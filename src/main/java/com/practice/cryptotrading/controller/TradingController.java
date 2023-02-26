package com.practice.cryptotrading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.cryptotrading.persistence.crypto.pricing.CryptoPricing;
import com.practice.cryptotrading.persistence.crypto.pricing.CryptoPricingService;
import com.practice.cryptotrading.persistence.crypto.transaction.Transaction;
import com.practice.cryptotrading.persistence.crypto.transaction.TransactionService;
import com.practice.cryptotrading.persistence.user.User;
import com.practice.cryptotrading.persistence.user.UserService;
import com.practice.cryptotrading.persistence.wallet.CryptoWallet;

/**
 * @author Sin Yee
 *
 */
@RestController
@RequestMapping(path = "/trading")
public class TradingController {

	@Autowired
	UserService userService;

	@Autowired
	CryptoPricingService cryptoPricingService;

	@Autowired
	TransactionService transactionService;

	public User getDefaultUser() {
		return userService.findUserByName("user");
	}

	@GetMapping(path = "bestprices")
	public List<CryptoPricing> getBestAggregatedPrice() {
		return cryptoPricingService.findAll();
	}

	@PostMapping(path = "trade")
	public User trade(@RequestParam(value = "cryptotype", required = true) String cryptoType,
			@RequestParam(value = "ordertype", required = true) String orderType,
			@RequestParam(value = "quantity", required = true) double quantity) {
		return userService.trade(getDefaultUser().getId(), cryptoType, orderType, quantity);
	}

	@GetMapping(path = "wallets")
	public List<CryptoWallet> getCryptoWallets() {
		return getDefaultUser().getCryptoWallets();
	}

	@GetMapping(path = "transactions")
	public List<Transaction> getTransactions() {
		return transactionService.listAll(getDefaultUser().getId());
	}

}
