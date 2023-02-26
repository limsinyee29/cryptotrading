package com.practice.cryptotrading.persistence.user;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.cryptotrading.controller.TradeRequest;
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
	public User trade(long id, TradeRequest request) {
		User user = findById(id);
		if (user.getWallet() == null) {
			throw new ServiceException(ErrorCode.WALLET_NOT_FOUND);
		}

		CryptoPricing cryptoPricing = cryptoPricingService.findByCryptoTypeAndOrderType(request.getCryptoType(),
				request.getOrderType());

		List<CryptoWallet> cryptoWallets = user.getCryptoWallets();
		// compute total amount
		double totalAmount = request.getQuantity() * cryptoPricing.getPrice();

		Optional<CryptoWallet> cyptoWalletOpt = cryptoWallets.stream()
				.filter(w -> w.getType().equalsIgnoreCase(request.getCryptoType())).findAny();
		CryptoWallet cyptoWallet = null;

		ZonedDateTime updatedAt = Utils.getCurrentTimeStamp();
		boolean success = false;
		if (CryptoPricing.ORDER_TYPE_BUY.equalsIgnoreCase(request.getOrderType())) {
			if (user.getWallet().getBalance() < totalAmount) {
				throw new ServiceException(ErrorCode.BALANCE_INSUFFICIENT);
			}
			if (cyptoWalletOpt.isPresent()) {
				cyptoWallet = cyptoWalletOpt.get();
				cyptoWallet.setQuantityBalance(cyptoWallet.getQuantityBalance() + request.getQuantity());
				cyptoWallet.setUpdatedAt(updatedAt);
			} else {
				cyptoWallet = new CryptoWallet(user.getId(), request.getCryptoType(), request.getQuantity());
				entityManager.persist(cyptoWallet);
				entityManager.flush();

			}
			user.getWallet().setBalance(user.getWallet().getBalance() - totalAmount);
			success = true;
		} else if (CryptoPricing.ORDER_TYPE_SELL.equalsIgnoreCase(request.getOrderType())) {
			if (!cyptoWalletOpt.isPresent()) {
				throw new ServiceException(ErrorCode.BALANCE_INSUFFICIENT);
			}
			cyptoWallet = cyptoWalletOpt.get();
			if (cyptoWallet.getQuantityBalance() < request.getQuantity()) {
				throw new ServiceException(ErrorCode.BALANCE_INSUFFICIENT);
			}
			cyptoWallet.setQuantityBalance(cyptoWallet.getQuantityBalance() - request.getQuantity());
			cyptoWallet.setUpdatedAt(updatedAt);
			user.getWallet().setBalance(user.getWallet().getBalance() + totalAmount);
			success = true;
		}
		if (success) {
			transactionService.add(new Transaction(user, user.getWallet(), cyptoWallet, request.getOrderType(),
					request.getQuantity(), cryptoPricing.getPrice(), totalAmount));
			user.getWallet().setUpdatedAt(updatedAt);
			user.setUpdatedAt(Utils.getCurrentTimeStamp());
		}
		return findById(id);
	}

	@Override
	public List<User> listAll() {
		return userRepository.findAll();
	}

}
