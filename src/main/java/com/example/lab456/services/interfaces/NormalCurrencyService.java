package com.example.lab456.services.interfaces;

import com.example.lab456.dto.crupdate.CrupdateCurrencyDTO;
import com.example.lab456.entities.CurrencyEntity;

import java.util.List;

public interface NormalCurrencyService {

    List<CurrencyEntity> getAll();

    List<CurrencyEntity> createAll(List<CrupdateCurrencyDTO> currencies);
}
