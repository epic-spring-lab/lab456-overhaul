package com.example.lab456.mappers;

import com.example.lab456.dto.crupdate.CrupdateExchangeRateDTO;
import com.example.lab456.entities.CurrencyEntity;
import com.example.lab456.entities.ExchangeDateEntity;
import com.example.lab456.entities.ExchangeRateEntity;

public class ExchangeRateMapper {
    public static ExchangeRateEntity toEntity(CrupdateExchangeRateDTO crupdateExchangeRateDTO) {
        return ExchangeRateEntity.builder()
                .rate(crupdateExchangeRateDTO.getRate())
                .exchangeDate(ExchangeDateEntity.builder().id(crupdateExchangeRateDTO.getExchangeDateId()).build())
                .sourceCurrency(CurrencyEntity.builder().id(crupdateExchangeRateDTO.getSourceCurrencyId()).build())
                .targetCurrency(CurrencyEntity.builder().id(crupdateExchangeRateDTO.getTargetCurrencyId()).build())
                .build();
    }
}
