package io.fortumo.dbhandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.fortumo.controller.SearchController;
import io.fortumo.domain.Payments;
import io.fortumo.repositories.PaymentsRepository;
import io.fortumo.util.Cache;
import io.fortumo.util.FortumoConstants;

/**
 * @author Dinesh Thangaraj
 *
 * Created on 06-Jan-2018
 */
@Component
public class DbHandlerImpl implements DbHandler {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private Cache cache;

	@Autowired
	private PaymentsRepository paymentsRepository;

	@Override
	public String paymentsSearch(final String merchantUuid, final String operatorCode, final String countryCode,
			final String msisdn) {
		String result = execute(QueryTypes.SEARCH_PAYMENT,
				new Object[] { msisdn, merchantUuid, operatorCode, countryCode });
		if (result == null)
			return FortumoConstants.NO_RECORDS_TEXT;
		return (String) result;
	}

	@Override
	public String generateReport(String merchantUuid, Integer days, Integer offset) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -offset);
		Date endDate = cal.getTime();
		cal.add(Calendar.DATE, -days);
		Date startDate = cal.getTime();
		String result = execute(QueryTypes.GENERATE_REPORT, new Object[] { merchantUuid, startDate, endDate });
		if (result == null)
			return FortumoConstants.NO_RECORDS_TEXT;
		return result;
	}

	private String execute(QueryTypes queryType, Object[] params) {
		String searchString = constructSearch(queryType, params);
		String result = null;
		synchronized (searchString) {
			if (cache.containsPaymentSearches(searchString)) {
				logger.debug("Found results in the cache for the search " + searchString);
				return cache.getPaymentSearches(searchString);
			}
			ExecutorService executor = null;
			Future<String> future = null;
			try {
				logger.debug("Executing the search " + searchString);
				executor = Executors.newSingleThreadExecutor();
				future = executor.submit(new Callable<String>() {
					@Override
					public String call() throws Exception {
						// Thread.sleep(5000);
						return talkToPersistenceLayer(paymentsRepository, queryType, params);
					}
				});
				result = future.get(FortumoConstants.TIMEOUTS.get(queryType), TimeUnit.MILLISECONDS);
				if (result != null) {
					// TODO In case of report generation, based on the use case, cache
					// location of csv or something similar, instead of entire result
					logger.debug("Caching the result for the search " + searchString);
					cache.putPaymentSearches(searchString, result);
				}
				else {
					logger.debug("No records found for the search " + searchString);
					return FortumoConstants.NO_RECORDS_TEXT;
				}
			}
			catch (TimeoutException e) {
				logger.error("Timeout in getting the results for the search " + searchString + ".", e);
				if (future != null)
					future.cancel(true);
				return FortumoConstants.SEARCH_TIMED_OUT_TEXT;
			}
			catch (Exception e) {
				logger.error("Exception in getting the results for the search " + searchString + ".", e);
				return FortumoConstants.SEARCH_TIMED_OUT_TEXT;
			}
			finally {
				if (executor != null)
					executor.shutdownNow();
				logger.debug("Notifying all the waiting threads on the search " + searchString);
				searchString.notifyAll();
			}
		}
		return result;
	}

	private static String talkToPersistenceLayer(PaymentsRepository paymentsRepository, final QueryTypes queryType,
			final Object[] params) throws Exception {
		if (QueryTypes.SEARCH_PAYMENT.equals(queryType)) {
			// TODO - extend this logic to support various combinations of search params
			List<Payments> payments = paymentsRepository.findByMsisdnAndMerchantUuidAndCountryCodeAndOperatorCode(
					(String) params[0], (String) params[1], (String) params[2], (String) params[3]);
			if (payments != null && !payments.isEmpty())
				return "Found record " + payments.get(0).getId() + " created at "
						+ new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(payments.get(0).getCreatedAt());
		}
		else if (QueryTypes.GENERATE_REPORT.equals(queryType)) {
			// TODO - Batch this to return results in chunks
			List<Payments> payments = paymentsRepository.findByMerchantUuidAndCreatedAtGreaterThanAndCreatedAtLessThan(
					(String) params[0], (Date) params[1], (Date) params[2]);
			logger.debug("Generating report for " + (String) params[0] + " from "
					+ new SimpleDateFormat("yyyy-MM-dd").format((Date) params[1]) + " till "
					+ new SimpleDateFormat("yyyy-MM-dd").format((Date) params[2]));
			StringBuilder sb = new StringBuilder();
			if (payments != null && !payments.isEmpty()) {
				// TODO - this can be further improved to return JSON or written to a csv
				// or streamed based on the requirement
				for (Payments payment : payments)
					sb.append(payment.toString()).append("\n");
				return sb.toString();
			}
		}
		return null;
	}

	private String constructSearch(QueryTypes queryType, Object[] params) {
		StringBuilder sb = new StringBuilder();
		for (Object param : params)
			if (param instanceof String)
				sb.append((String) param).append(":");
			else if (param instanceof Date) {
				sb.append(new SimpleDateFormat("yyyy-MM-dd").format((Date) param)).append(":");
			}
		return sb.toString().intern();
	}
}