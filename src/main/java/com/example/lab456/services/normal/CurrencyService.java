package com.example.lab456.services.normal;

import com.example.lab456.dto.CurrencyDTO;

public interface CurrencyService {

    Long createCurrency(CurrencyDTO currencyDTO);

    CurrencyDTO getCurrency(Long id);

    void updateCurrency(CurrencyDTO currencyDTO);

    void deleteCurrency(Long id);

}
