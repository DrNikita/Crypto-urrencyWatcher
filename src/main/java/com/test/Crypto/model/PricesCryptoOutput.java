package com.test.Crypto.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PricesCryptoOutput {

	private String id;
	private String name;
	private String symbol;
	private String rank;
	private String price_usd;
	private String price_btc;
	private String twentyFourHour_volume_usd;
	private String market_cap_usd;
	private String available_supply;
	private String total_supply;
	private String max_supply;
	private String percent_change_1h;
	private String percent_change_24h;
	private String percent_change_7d;
	private String last_updated;

	public PricesCryptoOutput(PricesCryptoOutputBuilder pricesCryptoOutputBuilder) {
		id = pricesCryptoOutputBuilder.id;
		name = pricesCryptoOutputBuilder.name;
		symbol = pricesCryptoOutputBuilder.symbol;
		rank = pricesCryptoOutputBuilder.rank;
		price_usd = pricesCryptoOutputBuilder.price_usd;
		price_btc = pricesCryptoOutputBuilder.price_btc;
		twentyFourHour_volume_usd = pricesCryptoOutputBuilder.twentyFourHour_volume_usd;
		market_cap_usd = pricesCryptoOutputBuilder.market_cap_usd;
		available_supply = pricesCryptoOutputBuilder.available_supply;
		total_supply = pricesCryptoOutputBuilder.total_supply;
		max_supply = pricesCryptoOutputBuilder.max_supply;
		percent_change_1h = pricesCryptoOutputBuilder.percent_change_1h;
		percent_change_24h = pricesCryptoOutputBuilder.percent_change_24h;
		percent_change_7d = pricesCryptoOutputBuilder.percent_change_7d;
		last_updated = pricesCryptoOutputBuilder.last_updated;
	}
}
