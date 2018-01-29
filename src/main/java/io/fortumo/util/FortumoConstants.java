package io.fortumo.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.fortumo.dbhandler.QueryTypes;

/**
 * @author Dinesh Thangaraj
 *
 * Created on 06-Jan-2018
 */
public class FortumoConstants {
	private FortumoConstants() { // Let this class not be initialized
	}

	public static final int MAX_CACHE_ENTRIES = 1000;

	public static final String NO_RECORDS_TEXT = "No records found";

	private static final int SEARCH_TIME_OUT = 5000;

	private static final int REPORT_TIME_OUT = 10 * 60 * 1000;

	public static final String SEARCH_TIMED_OUT_TEXT = "Search timed out";

	public static final String READ_TIMED_OUT_TEXT = "Read timed out";

	public static final int RETRY_COUNT = 3;

	public static final Map<QueryTypes, Integer> TIMEOUTS;

	static {
		Map<QueryTypes, Integer> _TIMEOUTS = new HashMap<QueryTypes, Integer>();
		_TIMEOUTS.put(QueryTypes.SEARCH_PAYMENT, SEARCH_TIME_OUT);
		_TIMEOUTS.put(QueryTypes.GENERATE_REPORT, REPORT_TIME_OUT);
		TIMEOUTS = Collections.unmodifiableMap(_TIMEOUTS);
	}

}
