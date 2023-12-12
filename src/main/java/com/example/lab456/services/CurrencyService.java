package com.example.lab456.services;

import com.example.lab456.dto.CurrencyDTO;

public interface CurrencyService {

    Long create(CurrencyDTO currencyDTO);

    CurrencyDTO get(Long id);

    void update(CurrencyDTO currencyDTO);

    void delete(Long id);

}
