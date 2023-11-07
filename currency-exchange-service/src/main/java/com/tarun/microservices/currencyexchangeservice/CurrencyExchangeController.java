package com.tarun.microservices.currencyexchangeservice;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tarun.microservices.currencyexchangeservice.CurrencyExchangeRepository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {

    // lets make use of JPA respository to handle data.sql data
    @Autowired
    CurrencyExchangeRepository repository;

    // to get the value of the code
    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        // CurrencyExchange currencyExchange = new CurrencyExchange(1001, from, to,
        // BigDecimal.valueOf(50));

        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
        if (currencyExchange == null) {
            throw new RuntimeException("Unable to find the data using details provided" + from + to);
        }
        currencyExchange.setEnviroment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }

    // this api retreives all the data present in database
    @GetMapping("/currency-exchange")
    public List<CurrencyExchange> getData() {
        List<CurrencyExchange> l = repository.findAll();
        return l;
    }

}
