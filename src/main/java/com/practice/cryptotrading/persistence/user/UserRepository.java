package com.practice.cryptotrading.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sin Yee
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByName(String name);
	
	User findById(long id);

}
