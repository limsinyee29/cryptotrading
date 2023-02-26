package com.practice.cryptotrading.persistence.crypto.pricing;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.cryptotrading.error.ErrorCode;
import com.practice.cryptotrading.exception.ServiceException;
import com.practice.cryptotrading.util.Utils;

/**
 * @author Sin Yee
 *
 */
@Service
public class CryptoPricingServiceImpl implements CryptoPricingService {

	@Autowired
	CryptoPricingRepository cpRepository;

	@Autowired
	private EntityManager entityManager;

	@Override
	public CryptoPricing findByCryptoTypeAndOrderType(String cryptoType, String orderType) {
		CryptoPricing cp = cpRepository.findByCryptoTypeAndOrderType(cryptoType, orderType);
		if (cp == null) {
			throw new ServiceException(ErrorCode.CRYPTO_PRICING_NOT_FOUND);
		}
		return cp;
	}

	@Override
	@Transactional
	public CryptoPricing update(String cryptoType, String orderType, double price) {
		CryptoPricing cp = cpRepository.findByCryptoTypeAndOrderType(cryptoType, orderType);
		if (cp == null) {
			cp = new CryptoPricing(cryptoType, orderType, price);
			entityManager.persist(cp);
		} else {
			cp.setPrice(price);
			cp.setUpdatedAt(Utils.getCurrentTimeStamp());
		}
		return cp;
	}

	@Override
	public List<CryptoPricing> findAll() {
		return cpRepository.findAll();
	}

}
