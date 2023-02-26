package com.practice.cryptotrading.persistence.crypto.pricing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Sin Yee
 *
 */
public interface CryptoPricingRepository extends JpaRepository<CryptoPricing, Long> {

	@Query("SELECT p FROM CryptoPricing p WHERE lower(p.cryptoType)=lower(:cryptoType) and lower(p.orderType)=lower(:orderType)")
	CryptoPricing findByCryptoTypeAndOrderType(String cryptoType, String orderType);

	List<CryptoPricing> findAll();

}
