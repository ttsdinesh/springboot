package io.fortumo.dbhandler;

/**
 * @author Dinesh Thangaraj
 *
 * Created on 06-Jan-2018
 */
public interface DbHandler {
	/**
	 * Responsible for searching and fetching Payments matching the input search criteria
	 * @param merchantUuid
	 * @param operatorCode
	 * @param countryCode
	 * @param msisdn
	 * @return Payments matching the input search criteria
	 */
	public String paymentsSearch(final String merchantUuid, final String operatorCode, final String countryCode,
			final String msisdn);

	/**
	 * Responsible for generating the report
	 * @param merchantUuid
	 * @param days (Numbers of days worth of data)
	 * @param offset (EndDate offset from current day)
	 * @return Returning String of List of Payments for time being and can be improved to
	 * return JSON, written to CSV, streamed to be downloaded or link sent via email based
	 * on the requirement
	 */
	public String generateReport(final String merchantUuid, final Integer days, final Integer offset);

	// TODO add any other contract here.
}
