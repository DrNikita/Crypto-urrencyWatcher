package com.test.Crypto.service;

import java.util.List;
import java.util.Optional;

import com.test.Crypto.model.Cryptocurrency;

public interface CryptocurrencyService {

	List<Cryptocurrency> findAll();

	void save(Cryptocurrency cryptocurrency);

	Optional<Cryptocurrency> findById(Long id);

	void deleteById(Long id);

	void updateCryptocurrencyValue(Long id, String value);

	Optional<Cryptocurrency> findCryptocurrencyBySymbol(String symbol);

}
