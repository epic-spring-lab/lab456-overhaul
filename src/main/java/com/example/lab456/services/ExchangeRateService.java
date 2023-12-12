package com.example.lab456.services;

import com.example.lab456.dto.ExchangeRateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeRateDTO;

public interface ExchangeRateService{

    Long create(CrupdateExchangeRateDTO exchangeRateDTO);

    ExchangeRateDTO get(Long id);

    void update(Long id, CrupdateExchangeRateDTO exchangeRateDTO);

    void delete(Long id);


}
