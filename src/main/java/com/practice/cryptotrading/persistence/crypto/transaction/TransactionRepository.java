package com.practice.cryptotrading.persistence.crypto.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Sin Yee
 *
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query("SELECT t FROM Transaction t where t.userId =:userId")
	Page<Transaction> findByUserId(long userId, Pageable pageable);

}
