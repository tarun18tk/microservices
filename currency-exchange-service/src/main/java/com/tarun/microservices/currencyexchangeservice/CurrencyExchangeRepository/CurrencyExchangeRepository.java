package com.tarun.microservices.currencyexchangeservice.CurrencyExchangeRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarun.microservices.currencyexchangeservice.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    //Spring JPA automatically creates a method that would search the database
    //by given parameters and return values
    CurrencyExchange findByFromAndTo(String from, String to);
    
}
