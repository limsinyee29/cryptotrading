package com.practice.cryptotrading.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;

import org.awaitility.Awaitility;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.practice.cryptotrading.schedule.Scheduler;

/**
 * @author Sin Yee
 *
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TradingControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testGetBestPrice() throws Exception {	
		mockMvc.perform(get("/trading/bestprice")).andExpect(status().isOk()).andReturn().getResponse();
	}
	
	@Test
	public void testTrade() throws Exception {	
		mockMvc.perform(post("/trading/trade?cryptotype=ETHUSDT&ordertype=buy&quantity=1")).andExpect(status().isOk()).andReturn().getResponse();
	}
	
	@Test
	public void testGetCryptoWallet() throws Exception {
		mockMvc.perform(get("/trading/wallets")).andExpect(status().isOk()).andReturn().getResponse();
	}
	
	@Test
	public void testGetTransactiin() throws Exception {	
		mockMvc.perform(get("/trading/transactions")).andExpect(status().isOk()).andReturn().getResponse();
	}

	

}