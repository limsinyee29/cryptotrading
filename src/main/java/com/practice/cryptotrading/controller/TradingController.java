package com.practice.cryptotrading.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.cryptotrading.persistence.crypto.pricing.CryptoPricing;
import com.practice.cryptotrading.persistence.crypto.pricing.CryptoPricingService;
import com.practice.cryptotrading.persistence.crypto.transaction.Transaction;
import com.practice.cryptotrading.persistence.crypto.transaction.TransactionService;
import com.practice.cryptotrading.persistence.user.User;
import com.practice.cryptotrading.persistence.user.UserResponse;
import com.practice.cryptotrading.persistence.user.UserService;
import com.practice.cryptotrading.persistence.wallet.CryptoWallet;

import io.swagger.v3.oas.annotations.Operation;

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

	@GetMapping(path = "loginuser")
	@Operation(summary = "Get Default User", description = "Get Default User")
	public ResponseEntity<UserResponse> getDefaultUser() {
		return ResponseEntity.ok(new UserResponse(userService.findUserByName("user")));
	}

	@GetMapping(path = "bestprices")
	@Operation(summary = "Get Latest Best Aggregated Price", description = "Get Latest Best Aggregated Price")
	public ResponseEntity<List<CryptoPricing>> getBestAggregatedPrice() {
		return ResponseEntity.ok(cryptoPricingService.findAll());
	}

	@PostMapping(path = "{userId}/trade")
	@Operation(summary = "Trade", description = "Trade with input cryptoType=\"ETHUSDT\" OR \"BTCUSDT\", orderType=\"BUY\" OR \"SELL\", quantity=[greater than 0] ")
	public ResponseEntity<User> trade(@PathVariable("userId") long userId, @Valid @RequestBody TradeRequest request) {
		return ResponseEntity.ok(userService.trade(userId, request));
	}

	@GetMapping(path = "{userId}/wallets")
	@Operation(summary = "Get User's Crypto Wallets", description = "Get User's Crypto Wallets")
	public ResponseEntity<List<CryptoWallet>> getCryptoWallets(@PathVariable("userId") long userId) {
		return ResponseEntity.ok(userService.findById(userId).getCryptoWallets());
	}

	@GetMapping(path = "{userId}/transactions")
	@Operation(summary = "Get User's Trading History", description = "Get User's Trading History")
	public ResponseEntity<Page<Transaction>> getTransactions(@PathVariable("userId") long userId, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "sort", required = false, defaultValue = "createdAt") String orderBy,
			@RequestParam(value = "ordering", required = false, defaultValue = "desc") String orderDirection) {
		userService.findById(userId);
		return ResponseEntity.ok(transactionService.listAll(userId, page, pageSize, orderBy, orderDirection));
	}

}
