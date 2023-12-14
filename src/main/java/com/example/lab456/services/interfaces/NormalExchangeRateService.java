package com.example.lab456.services.interfaces;

import com.example.lab456.dto.ExchangeRateDTO;
import com.example.lab456.entities.ExchangeRateEntity;

import java.util.List;

public interface NormalExchangeRateService {

    void deleteAll();

    List<ExchangeRateEntity> createAll(List<ExchangeRateEntity> exchanges);

    List<ExchangeRateDTO> getAll();

    List<ExchangeRateDTO> getAllTodayExchanges();

    List<ExchangeRateDTO> getAllByCurrencyAndDate(int day, int month, int year, String source, String target);

    List<ExchangeRateDTO> getAllExchangesByPair(String source, String target);
}
