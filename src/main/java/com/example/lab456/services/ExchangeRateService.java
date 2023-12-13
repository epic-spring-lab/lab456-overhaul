package com.example.lab456.services;

import com.example.lab456.dto.ExchangeRateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeRateDTO;
import com.example.lab456.entities.ExchangeRateEntity;

import java.util.List;

public interface ExchangeRateService {

    List<ExchangeRateDTO> getAll();

    List<ExchangeRateDTO> getAllTodayExchanges();

    List<ExchangeRateDTO> getAllByCurrencyAndDate(int day, int month, int year, String source, String target);

    List<ExchangeRateDTO> getAllExchangesByPair(String source, String target);

    Long create(CrupdateExchangeRateDTO exchangeRateDTO);

    ExchangeRateDTO get(Long id);

    void update(Long id, CrupdateExchangeRateDTO exchangeRateDTO);

    void delete(Long id);

    void deleteAll();

    List<ExchangeRateEntity> createAll(List<ExchangeRateEntity> exchanges);
}
