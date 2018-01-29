package io.fortumo;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.fortumo.dbhandler.QueryTypes;
import io.fortumo.util.FortumoConstants;

/**
 * @author Dinesh Thangaraj
 *
 * Created on 06-Jan-2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchControllerTest {

	// Global values
	String merchantUuid = "bff217d9e6aaf6bcdd1ae3229a76fbdb";

	String operatorCode = "operator-60";

	String countryCode = "BB";

	String msisdn = "373096722";

	// ======================== TEST CASE 1 ========================
	@Test
	public void testServerStatus() throws Exception {
		try {
			URL url = new URL("http://localhost:12000/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(2000);
			assertEquals(conn.getResponseCode(), 200);
		}
		catch (Exception e) {
			throw new Exception();
		}
	}

	// ======================== TEST CASE 2 ========================
	@Test
	public void testPaymentSearch() throws Exception {
		// NOTE : ensure providing right details here.
		String merchantUuid = "bff217d9e6aaf6bcdd1ae3229a76fbdb";
		String operatorCode = "operator-60";
		String countryCode = "BB";
		String msisdn = "373096722";
		BufferedReader br = null;
		try {
			URL url = new URL(urlBuilder(QueryTypes.SEARCH_PAYMENT,
					new Object[] { "paymentsSearch", merchantUuid, operatorCode, countryCode, msisdn }));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(FortumoConstants.TIMEOUTS.get(QueryTypes.SEARCH_PAYMENT));

			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output = br.readLine();
			assertEquals(conn.getResponseCode(), 200);
			assertNotEquals(output, FortumoConstants.NO_RECORDS_TEXT);
			assertNotEquals(output, FortumoConstants.SEARCH_TIMED_OUT_TEXT);
			// NOTE : ensure providing right details here.
			assertEquals(output, "Found record 7533618 created at 2013-03-29 16:23:14");
			br.close();
		}
		catch (Exception e) {
			throw new Exception();
		}
		finally {
			if (br != null)
				try {
					br.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	// ======================== TEST CASE 3 ========================
	@Test
	public void testInvalidPaymentSearch() throws Exception {
		String merchantUuid = "def3d7e54593a7bdff9ba77d8a12908f";
		String operatorCode = "xxxxxxxxxxxx";
		String countryCode = "IN";
		String msisdn = "373066489";
		BufferedReader br = null;
		try {
			URL url = new URL(urlBuilder(QueryTypes.SEARCH_PAYMENT,
					new Object[] { "paymentsSearch", merchantUuid, operatorCode, countryCode, msisdn }));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(FortumoConstants.TIMEOUTS.get(QueryTypes.SEARCH_PAYMENT));

			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output = br.readLine();
			assertEquals(conn.getResponseCode(), 200);
			assertEquals(output, FortumoConstants.NO_RECORDS_TEXT);
			br.close();
		}
		catch (Exception e) {
			throw new Exception(e);
		}
		finally {
			if (br != null)
				try {
					br.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	// ======================== TEST CASE 4 ========================
	@Test
	public void test1DayReport() throws Exception {
		String merchantUuid = "bff217d9e6aaf6bcdd1ae3229a76fbdb";
		int days = 1;
		int offset = 1;
		BufferedReader br = null;
		try {
			URL url = new URL(urlBuilder(QueryTypes.GENERATE_REPORT,
					new Object[] { "generateReport", merchantUuid, days, offset }));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// 20 seconds wait
			conn.setReadTimeout(FortumoConstants.TIMEOUTS.get(QueryTypes.GENERATE_REPORT) / 30);
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output = br.readLine();
			assertEquals(conn.getResponseCode(), 200);
			assertNotEquals(output, FortumoConstants.NO_RECORDS_TEXT);
			assertNotEquals(output, FortumoConstants.SEARCH_TIMED_OUT_TEXT);
			br.close();
		}
		catch (Exception e) {
			throw new Exception(e);
		}
		finally {
			if (br != null)
				try {
					br.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	// ======================== TEST CASE 5 ========================
	@Test
	public void test90DayReport() throws Exception {
		String merchantUuid = "bff217d9e6aaf6bcdd1ae3229a76fbdb";
		int days = 90;
		int offset = 1;
		BufferedReader br = null;
		try {
			URL url = new URL(urlBuilder(QueryTypes.GENERATE_REPORT,
					new Object[] { "generateReport", merchantUuid, days, offset }));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(FortumoConstants.TIMEOUTS.get(QueryTypes.GENERATE_REPORT));
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output = br.readLine();
			assertEquals(conn.getResponseCode(), 200);
			assertNotEquals(output, FortumoConstants.NO_RECORDS_TEXT);
			assertNotEquals(output, FortumoConstants.SEARCH_TIMED_OUT_TEXT);
			br.close();
		}
		catch (Exception e) {
			throw new Exception(e);
		}
		finally {
			if (br != null)
				try {
					br.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	// ======================== TEST CASE 6 ========================
	@Test
	public void test90DayReportTimeout() throws Exception {
		String merchantUuid = "bff217d9e6aaf6bcdd1ae3229a76fbdb";
		int days = 90;
		int offset = 1;
		BufferedReader br = null;
		try {
			URL url = new URL(urlBuilder(QueryTypes.GENERATE_REPORT,
					new Object[] { "generateReport", merchantUuid, days, offset }));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(500);
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			br.close();
		}
		catch (Exception e) {
			assertEquals(e.getMessage(), FortumoConstants.READ_TIMED_OUT_TEXT);
		}
		finally {
			if (br != null)
				try {
					br.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	// ======================== TEST CASE 7 ========================
	@Test
	public void basicLoadTest() throws Exception {
		final SearchControllerTest searchControllerTest = new SearchControllerTest();
		IntStream.range(0, 10000).parallel().forEach(n -> {
			try {
				searchControllerTest.test1DayReport();
				searchControllerTest.test90DayReport();
				searchControllerTest.testPaymentSearch();
				searchControllerTest.testServerStatus();
			}
			catch (Exception e) {
				assertFalse(e.getMessage(), true);
			}
		});
	}

	private String urlBuilder(QueryTypes queryType, Object[] params) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://localhost:12000/");
		if (QueryTypes.SEARCH_PAYMENT.equals(queryType)) {
			sb.append((String) params[0]);
			sb.append("?merchantUuid=");
			sb.append((String) params[1]);
			sb.append("&operatorCode=");
			sb.append((String) params[2]);
			sb.append("&countryCode=");
			sb.append((String) params[3]);
			sb.append("&msisdn=");
			sb.append((String) params[4]);
		}
		else if (QueryTypes.GENERATE_REPORT.equals(queryType)) {
			sb.append((String) params[0]);
			sb.append("?merchantUuid=");
			sb.append((String) params[1]);
			sb.append("&days=");
			sb.append((Integer) params[2]);
			sb.append("&offset=");
			sb.append((Integer) params[3]);
		}
		return sb.toString();
	}
}
