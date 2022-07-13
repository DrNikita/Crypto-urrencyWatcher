package com.test.Crypto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.Crypto.model.Cryptocurrency;

@Repository
public interface CryptoRepository extends JpaRepository<Cryptocurrency, Long> {

	@Query("SELECT c FROM Cryptocurrency c WHERE c.symbol = ?1")
	Optional<Cryptocurrency> findCryptocurrencyBySymbol(String symbol);

}
