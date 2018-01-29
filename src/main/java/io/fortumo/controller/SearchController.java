package io.fortumo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.fortumo.dbhandler.DbHandlerImpl;

/**
 * @author Dinesh Thangaraj
 *
 * Created on 06-Jan-2018
 */
@Controller
public class SearchController {
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	DbHandlerImpl paymentsDao;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String index() {
		return "\t Welcome to Fortumo Test Task.\n\t Author: Dinesh Thangaraj <ttsdinesh@gmail.com>";
	}

	@RequestMapping(value = "/paymentsSearch", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String paymentsSearch(@RequestParam(name = "merchantUuid", required = true) String merchantUuid,
			@RequestParam(name = "operatorCode", required = true) String operatorCode,
			@RequestParam(name = "countryCode", required = true) String countryCode,
			@RequestParam(name = "msisdn", required = true) String msisdn) {
		logger.debug("===========================================");
		logger.debug("merchantUuid: " + merchantUuid);
		logger.debug("operatorCode: " + operatorCode);
		logger.debug("countryCode: " + countryCode);
		logger.debug("msisdn: " + msisdn);
		return paymentsDao.paymentsSearch(merchantUuid.trim(), countryCode.trim(), operatorCode.trim(), msisdn.trim());
	}

	@RequestMapping(value = "/generateReport", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String generateReport(@RequestParam(name = "merchantUuid", required = true) String merchantUuid,
			@RequestParam(name = "days", required = true, defaultValue = "1") Integer days,
			@RequestParam(name = "offset", required = true, defaultValue = "1") Integer offset) {
		logger.debug("===========================================");
		logger.debug("merchantUuid: " + merchantUuid);
		logger.debug("days: " + days);
		logger.debug("offset: " + offset);

		if (days < 1 || offset < 1)
			return "Days or Offset could not be less than 1.";
		if (days > 180)
			return "Please generate report for less than 180 days.";

		return paymentsDao.generateReport(merchantUuid, days, offset);
	}
}
