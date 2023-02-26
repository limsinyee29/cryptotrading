package com.practice.cryptotrading.persistence.crypto.transaction;

import org.springframework.data.domain.Page;

/**
 * @author Sin Yee
 *
 */
public interface TransactionService {

	/**
	 * List user's transaction history
	 * 
	 * @param userId
	 * @return transaction histories
	 */
	Page<Transaction> listAll(Long userId, int page, int pageSize, String orderBy, String orderDirection);

	/**
	 * Add transaction record
	 * 
	 * @param transaction
	 * @return transaction
	 */
	Transaction add(Transaction transaction);

}
