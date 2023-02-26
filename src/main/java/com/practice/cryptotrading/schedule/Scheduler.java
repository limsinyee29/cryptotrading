package com.practice.cryptotrading.schedule;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.practice.cryptotrading.persistence.crypto.pricing.CryptoPricing;
import com.practice.cryptotrading.persistence.crypto.pricing.CryptoPricingService;
import com.practice.cryptotrading.persistence.crypto.pricing.CryptoType;
import com.practice.cryptotrading.persistence.crypto.source.Binance;
import com.practice.cryptotrading.persistence.crypto.source.Houbi;
import com.practice.cryptotrading.restservice.RestService;

/**
 * @author Sin Yee
 *
 */
@Component
public class Scheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

	@Autowired
	RestService restService;

	@Autowired
	CryptoPricingService cryptoPricingService;

	@Scheduled(cron = "*/10 * * * * *")
	public void bestPriceHandler() {

		List<Binance> binanceList = null;
		List<Houbi> houbiList = null;
		Binance[] binanceData = restService.get("https://api.binance.com/api/v3/ticker/bookTicker", Binance[].class);
		if (binanceData != null && binanceData.length > 0) {
			binanceList = Arrays.asList(binanceData);
		}
		Houbi[] houbiData = restService.get("https://api.huobi.pro/market/tickers", "data", Houbi[].class);
		if (houbiData != null && houbiData.length > 0) {
			houbiList = Arrays.asList(houbiData);
		}

		for (CryptoType cryptoTradingType : CryptoType.values()) {
			double highestBidPrice = Double.MIN_VALUE;
			double lowestAskPrice = Double.MAX_VALUE;
			
			if (binanceList != null) {
				Optional<Double> bidPrice = binanceList.stream()
						.filter(b -> b.getSymbol().equalsIgnoreCase(cryptoTradingType.name())).map(Binance::getBidPrice)
						.max(Double::compare);
				if (bidPrice.isPresent()) {
//					String log="Binance bidPrice ("+cryptoTradingType.name()+") "+bidPrice.get();
//					logger.info(log);
					highestBidPrice = Math.max(highestBidPrice, bidPrice.get());
				}
				Optional<Double> askPrice = binanceList.stream()
						.filter(b -> b.getSymbol().equalsIgnoreCase(cryptoTradingType.name())).map(Binance::getAskPrice)
						.min(Double::compare);
				if (askPrice.isPresent()) {
//					String log="Binance askPrice ("+cryptoTradingType.name()+") "+askPrice.get();
//					logger.info(log);
					lowestAskPrice = Math.min(lowestAskPrice, askPrice.get());
				}

			}
			if (houbiList != null) {
				Optional<Double> bidPrice = houbiList.stream()
						.filter(b -> b.getSymbol().equalsIgnoreCase(cryptoTradingType.name()))
						.map(h -> h.getBid()).max(Double::compare);
				if (bidPrice.isPresent()) {
//					String log="Houbi bidPrice ("+cryptoTradingType.name()+") "+bidPrice.get();
//					logger.info(log);
					highestBidPrice = Math.max(highestBidPrice, bidPrice.get());
				}
				Optional<Double> askPrice = houbiList.stream()
						.filter(b -> b.getSymbol().equalsIgnoreCase(cryptoTradingType.name()))
						.map(h -> h.getAsk()).min(Double::compare);
				if (askPrice.isPresent()) {
//					String log="Houbi askPrice ("+cryptoTradingType.name()+") "+askPrice.get();
//					logger.info(log);
					lowestAskPrice = Math.min(lowestAskPrice, askPrice.get());
				}
			}
			logger.info("highestBidPrice "+highestBidPrice);
			cryptoPricingService.update(cryptoTradingType.name(), CryptoPricing.ORDER_TYPE_SELL, highestBidPrice);
			
			logger.info("lowestAskPrice "+lowestAskPrice);
			cryptoPricingService.update(cryptoTradingType.name(), CryptoPricing.ORDER_TYPE_BUY, lowestAskPrice);
		}

	}

}
