package com.practice.cryptotrading.persistence.user;

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
	User trade(long id, String cryptoType, String orderType, double quantity);

}
