package com.test.Crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.test.Crypto.model.PricesCryptoOutput;

@Component
public class PricesRestClient {

	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<PricesCryptoOutput[]> getCryptoInformation(String id) {
		System.out.println("API CoinMarketCap");
		return restTemplate.getForEntity("https://api.coinlore.net/api/ticker/?id=" + id, PricesCryptoOutput[].class);
	}
}
