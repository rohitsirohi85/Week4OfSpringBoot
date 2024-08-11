package com.CurrencyConverter.CurrencyConverter.dto;

import java.util.Map;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyData {

    Map<String, Double> data;
}
