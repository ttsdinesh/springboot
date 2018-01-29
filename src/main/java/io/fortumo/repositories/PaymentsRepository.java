
package io.fortumo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.fortumo.domain.Payments;

/**
 * @author Dinesh Thangaraj
 *
 * Created on 06-Jan-2018
 */
@Repository
public interface PaymentsRepository extends JpaRepository<Payments, String> {
	/**
	 * Equivalent to SELECT * FROM payments WHERE msisdn=? AND merchant_uuid=? AND
	 * country_code=? AND operator_code=?
	 * 
	 * @param msisdn
	 * @param merchantUuid
	 * @param countryCode
	 * @param operatorCode
	 * @return List of Payments
	 */
	List<Payments> findByMsisdnAndMerchantUuidAndCountryCodeAndOperatorCode(final String msisdn,
			final String merchantUuid, final String countryCode, final String operatorCode);

	/**
	 * Equivalent to SELECT * FROM payments WHERE merchant_uuid=? AND created_at >
	 * fromDate AND created_at < toDate
	 * 
	 * @param msisdn
	 * @param merchantUuid
	 * @param countryCode
	 * @param operatorCode
	 * @return List of Payments
	 */
	List<Payments> findByMerchantUuidAndCreatedAtGreaterThanAndCreatedAtLessThan(final String merchantUuid,
			final Date fromDate, final Date toDate);
}
