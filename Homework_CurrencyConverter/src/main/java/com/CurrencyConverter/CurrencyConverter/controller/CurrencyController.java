package com.CurrencyConverter.CurrencyConverter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CurrencyConverter.CurrencyConverter.client.impl.CurrencyConverterClientImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyConverterClientImpl currencyConverterClient;
    //http://localhost:8080/currency/convertCurrency?fromCurrency=INR&toCurrency=USD&units=500
 
    @GetMapping("/convertCurrency")
    public ResponseEntity<Double> getRate(@RequestParam String fromCurrency, @RequestParam String toCurrency, @RequestParam Double units){

        return ResponseEntity.ok(currencyConverterClient.convertCurrency(fromCurrency, toCurrency, units));
    }
}
