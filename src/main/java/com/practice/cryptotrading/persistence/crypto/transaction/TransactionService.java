package com.practice.cryptotrading.persistence.crypto.transaction;

import java.util.List;

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
	List<Transaction> listAll(Long userId);
	
	/**
	 * Add transaction record
	 * 
	 * @param transaction
	 * @return transaction
	 */
	Transaction add(Transaction transaction);

}
