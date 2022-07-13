package com.test.Crypto.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.test.Crypto.model.Cryptocurrency;
import com.test.Crypto.model.PricesCryptoOutput;
import com.test.Crypto.model.User;
import com.test.Crypto.service.CryptocurrencyServiceImpl;
import com.test.Crypto.service.UserServiceImpl;

@RestController
@ComponentScan(basePackages = { "com.test.Crypto.repository" })
public class CryptocurrencyController {

	@Autowired
	private PricesRestClient client;

	@Autowired
	private CryptocurrencyServiceImpl cryptocurrencyServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;

	private final long SECOND = 1000;
	private final long MINUTE = SECOND * 60;

	@Scheduled(fixedDelay = MINUTE)
	void updateCryptocurrencyValues() {

		List<Cryptocurrency> list = cryptocurrencyServiceImpl.findAll();
		List<String> cryptocurrencyIds = list.stream().map(x -> x.getId().toString()).collect(Collectors.toList());

		String cryptocurrencyIdsStr = cryptocurrencyIds.toString().replace("[", "").replace("]", "").replace(", ", ",");

		ResponseEntity<PricesCryptoOutput[]> cryptocurrencies = client.getCryptoInformation(cryptocurrencyIdsStr);

		Arrays.stream(cryptocurrencies.getBody()).forEach(
				x -> cryptocurrencyServiceImpl.updateCryptocurrencyValue(Long.parseLong(x.getId()), x.getPrice_usd()));

		checkPrice();
	}

	private void checkPrice() {

		List<User> users = userServiceImpl.findAll();
		Iterator<User> it = users.iterator();

		while (it.hasNext()) {

			User u = it.next();

			BigDecimal currentValue = cryptocurrencyServiceImpl.findCryptocurrencyBySymbol(u.getCryptocurrencySymbol())
					.get().getValue();

			BigDecimal difference = currentValue.subtract(u.getCurrentPrice());

			int compare = currentValue.multiply(new BigDecimal(0.01)).compareTo(difference);

			if (compare > 0 || compare < 0) {
				userServiceImpl.updateUserEvent(u.getId(), "Price of " + u.getCryptocurrencySymbol() + " changed: "
						+ u.getCurrentPrice() + "$$ ===>" + currentValue + "$$");
			}
		}
	}

	@GetMapping("/")
	public ModelAndView showAllCryptocurrencies() {
		ModelAndView welcomePage = new ModelAndView("welcome");
		welcomePage.addObject("cryptocurrencies", cryptocurrencyServiceImpl.findAll());
		return welcomePage;
	}

	@RequestMapping(value = "/currentvalue")
	public ModelAndView cryptocurrencyActualPrice(@RequestParam String symbol) {
		ModelAndView page = new ModelAndView("currentvalue");

		updateCryptocurrencyValues();

		Cryptocurrency cryptocurrency = cryptocurrencyServiceImpl.findCryptocurrencyBySymbol(symbol)
				.orElseThrow(() -> new IllegalStateException("crypto with symbol: " + symbol + " does not exists"));

		page.addObject("symbol", cryptocurrency);
		return page;
	}

	@RequestMapping("/tracking")
	public ModelAndView createUser() {
		ModelAndView page = new ModelAndView("registration");
		page.addObject("user", new User());
		return page;
	}

	@RequestMapping(value = "/tracking", method = RequestMethod.POST)
	public ModelAndView cryptocurrencyTrackin(@ModelAttribute("user") User user, HttpServletRequest request) {
		ModelAndView page = new ModelAndView("cryptotracking");

		Optional<Cryptocurrency> cryptocurrency = cryptocurrencyServiceImpl
				.findCryptocurrencyBySymbol(user.getCryptocurrencySymbol());

		HttpSession session = request.getSession();

		if (cryptocurrency.isPresent()) {
			BigDecimal currentPrice = cryptocurrency.get().getValue();

			Optional<User> dbUser = userServiceImpl.findUserByName(user.getName());

			if (dbUser.isPresent()) {
				user.setCurrentPrice(currentPrice);
				userServiceImpl.updateUser(dbUser.get().getId(), user);
				session.setAttribute("user", dbUser.get());
			} else {
				user.setCurrentPrice(currentPrice);
				userServiceImpl.save(user);
				dbUser = userServiceImpl.findUserByName(user.getName());
				session.setAttribute("user", dbUser.get());
			}
		}
		page.addObject("us", session.getAttribute("user"));

		return page;
	}

}
