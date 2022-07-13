package com.test.Crypto.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.Crypto.model.Cryptocurrency;
import com.test.Crypto.repository.CryptoRepository;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

	@Autowired
	CryptoRepository cryptoRepository;

	@Override
	public List<Cryptocurrency> findAll() {
		return cryptoRepository.findAll();
	}

	@Override
	public void save(Cryptocurrency cryptocurrency) {
		cryptoRepository.save(cryptocurrency);
	}

	@Override
	public Optional<Cryptocurrency> findById(Long id) {
		return cryptoRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		cryptoRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void updateCryptocurrencyValue(Long id, String value) {
		Cryptocurrency cryptocurrency = cryptoRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("cyptocurrency with id: " + id + " does not exists"));

		if (value != null) {
			cryptocurrency.setValue(new BigDecimal(value));
		}
	}

	@Override
	public Optional<Cryptocurrency> findCryptocurrencyBySymbol(String symbol) {
		return cryptoRepository.findCryptocurrencyBySymbol(symbol);
	}
}
