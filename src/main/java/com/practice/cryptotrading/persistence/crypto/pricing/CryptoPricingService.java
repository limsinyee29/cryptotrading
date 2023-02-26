package com.practice.cryptotrading.persistence.crypto.pricing;

import java.util.List;

/**
 * @author Sin Yee
 *
 */
public interface CryptoPricingService {
	
	/**
	 * Find pricing by crypto type and order type
	 * 
	 * @param cryptoType
	 * @param orderType
	 * @return
	 */
	CryptoPricing findByCryptoTypeAndOrderType(String cryptoType, String orderType);
	
	/**
	 * Update or insert crypto pricing 
	 * 
	 * @param cryptoType
	 * @param pricingType
	 * @param price
	 * @return
	 */
	CryptoPricing update(String cryptoType, String pricingType, double price);
	
	/**
	 * List all the crypto pricing
	 * 
	 * @return
	 */
	List<CryptoPricing> findAll();

}
