package com.example.lab456.dao;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class ExchangeRateDAO {

    private Long id;
    private double rate;
    private Long sourceCurrencyId;
    private Long targetCurrencyId;
    private Long exchangeDateId;
}
