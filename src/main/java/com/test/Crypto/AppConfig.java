package com.test.Crypto;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.test.Crypto.model.Cryptocurrency;
import com.test.Crypto.repository.CryptoRepository;

@Configuration
public class AppConfig {

	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	CommandLineRunner initDatabase(CryptoRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Cryptocurrency(90L, "BTC", BigDecimal.valueOf(0))));
			log.info("Preloading " + repository.save(new Cryptocurrency(80L, "ETH", BigDecimal.valueOf(0))));
			log.info("Preloading " + repository.save(new Cryptocurrency(48543L, "SOL", BigDecimal.valueOf(0))));
		};
	}
}
