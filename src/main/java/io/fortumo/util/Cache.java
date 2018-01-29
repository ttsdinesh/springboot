package io.fortumo.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Dinesh Thangaraj
 *
 * Created on 06-Jan-2018
 */
@Component
@Scope("singleton")
public class Cache {

	Map<String, String> paymentSearches = new LinkedHashMap<String, String>(FortumoConstants.MAX_CACHE_ENTRIES + 1,
			.75F, false) {
		protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
			return size() > FortumoConstants.MAX_CACHE_ENTRIES;
		}
	};

	public void putPaymentSearches(String searchString, String results) {
		paymentSearches.put(searchString, results);
	}

	public String getPaymentSearches(String searchString) {
		return paymentSearches.get(searchString);
	}

	public boolean containsPaymentSearches(String searchString) {
		return paymentSearches.containsKey(searchString);
	}

}
