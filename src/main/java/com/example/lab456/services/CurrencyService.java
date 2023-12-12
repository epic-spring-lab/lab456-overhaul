package com.example.lab456.services;

import com.example.lab456.dto.CurrencyDTO;
import com.example.lab456.dto.crupdate.CrupdateCurrencyDTO;

public interface CurrencyService {

    Long create(CrupdateCurrencyDTO currencyDTO);

    CurrencyDTO get(Long id);

    void update(Long id, CrupdateCurrencyDTO currencyDTO);

    void delete(Long id);

}
