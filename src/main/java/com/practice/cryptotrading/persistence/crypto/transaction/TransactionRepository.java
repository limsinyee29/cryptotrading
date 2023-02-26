package com.practice.cryptotrading.persistence.crypto.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Sin Yee
 *
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query("SELECT t FROM Transaction t where t.user.id =:userId order by t.createdAt desc")
	List<Transaction> findByUserId(long userId);

}
