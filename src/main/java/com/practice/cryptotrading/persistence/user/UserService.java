package com.practice.cryptotrading.persistence.user;

import java.util.List;

import com.practice.cryptotrading.controller.TradeRequest;

/**
 * @author Sin Yee
 *
 */
public interface UserService {

	/**
	 * Find user by name
	 * 
	 * @param email
	 * @return user
	 */
	User findUserByName(String email);

	/**
	 * Find user by id
	 * 
	 * @param id
	 * @return user
	 */
	User findById(long id);

	/**
	 * Trade using the best price
	 * 
	 * @param id
	 * @param cryptoType
	 * @param orderTypOe
	 * @param quantity
	 * @return user
	 */
	User trade(long id, TradeRequest request);
	
	/**
	 * List all user
	 * 
	 * @return users
	 */
	List<User> listAll();

}
