package com.test.Crypto.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "cryptocurrencies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cryptocurrency {

	@Id
	private Long id;

	@Column(name = "symbol")
	private String symbol;

	@Column(name = "currency_value")
	private BigDecimal value;
}
