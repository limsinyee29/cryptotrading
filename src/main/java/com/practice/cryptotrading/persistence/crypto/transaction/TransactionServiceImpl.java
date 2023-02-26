package com.practice.cryptotrading.persistence.crypto.transaction;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sin Yee
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired 
	TransactionRepository transactionRepository;

	@Override
	public List<Transaction> listAll(Long userId) {
		return transactionRepository.findByUserId(userId);
	}

	@Override
	public Transaction add(Transaction transaction) {
		entityManager.persist(transaction);
		return transaction;
	}

}
