package com.practice.cryptotrading.persistence.user;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.cryptotrading.error.ErrorCode;
import com.practice.cryptotrading.exception.ServiceException;
import com.practice.cryptotrading.persistence.crypto.pricing.CryptoPricing;
import com.practice.cryptotrading.persistence.crypto.pricing.CryptoPricingService;
import com.practice.cryptotrading.persistence.crypto.transaction.Transaction;
import com.practice.cryptotrading.persistence.crypto.transaction.TransactionService;
import com.practice.cryptotrading.persistence.wallet.CryptoWallet;
import com.practice.cryptotrading.util.Utils;

/**
 * @author Sin Yee
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	EntityManager entityManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CryptoPricingService cryptoPricingService;

	@Autowired
	TransactionService transactionService;

	@Override
	public User findUserByName(String name) {
		User user = userRepository.findByName(name);
		if (user == null) {
			throw new ServiceException(ErrorCode.USER_NOT_FOUND);
		}
		return user;
	}

	@Override
	public User findById(long id) {
		User user = userRepository.findById(id);
		if (user == null) {
			throw new ServiceException(ErrorCode.USER_NOT_FOUND);
		}
		return user;
	}

	@Override
	@Transactional
	public User trade(long id, String cryptoType, String orderType, double quantity) {
		User user = findById(id);
		if (user.getWallet() == null) {
			throw new ServiceException(ErrorCode.WALLET_NOT_FOUND);
		}
		
		CryptoPricing cryptoPricing = cryptoPricingService.findByCryptoTypeAndOrderType(cryptoType, orderType);

		List<CryptoWallet> cryptoWallets = user.getCryptoWallets();
		//compute total amount
		double totalAmount = quantity * cryptoPricing.getPrice();
		
		Optional<CryptoWallet> cyptoWalletOpt = cryptoWallets.stream()
				.filter(w -> w.getType().equalsIgnoreCase(cryptoType)).findAny();
		CryptoWallet cyptoWallet = null;
		
		if (CryptoPricing.ORDER_TYPE_BUY.equalsIgnoreCase(orderType)) {
			if (user.getWallet().getBalance() < totalAmount) {
				throw new ServiceException(ErrorCode.BALANCE_INSUFFICIENT);
			}
			if (cyptoWalletOpt.isPresent()) {
				cyptoWallet = cyptoWalletOpt.get();
				cyptoWallet.setQuantityBalance(cyptoWallet.getQuantityBalance() + quantity);
				cyptoWallet.setUpdatedAt(Utils.getCurrentTimeStamp());
			} else {
				cyptoWallet = new CryptoWallet(user.getId(), cryptoType, quantity);
				entityManager.persist(cyptoWallet);
				entityManager.flush();
				
			}
			user.getWallet().setBalance(user.getWallet().getBalance() - totalAmount);
			
			transactionService.add(new Transaction(user, user.getWallet(), cyptoWallet, orderType, quantity,
					cryptoPricing.getPrice(), totalAmount));
			
		} else if (CryptoPricing.ORDER_TYPE_SELL.equalsIgnoreCase(orderType)) {
			if (!cyptoWalletOpt.isPresent()) {
				throw new ServiceException(ErrorCode.BALANCE_INSUFFICIENT);
			}
			cyptoWallet = cyptoWalletOpt.get();
			if (cyptoWallet.getQuantityBalance() < quantity) {
				throw new ServiceException(ErrorCode.BALANCE_INSUFFICIENT);
			}
			cyptoWallet.setQuantityBalance(cyptoWallet.getQuantityBalance() - quantity);
			cyptoWallet.setUpdatedAt(Utils.getCurrentTimeStamp());
			user.getWallet().setBalance(user.getWallet().getBalance() + totalAmount);
			transactionService.add(new Transaction(user, user.getWallet(), cyptoWallet, orderType, quantity,
					cryptoPricing.getPrice(), totalAmount));
		}
		return user;
	}

}
