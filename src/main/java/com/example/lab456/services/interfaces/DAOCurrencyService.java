package com.example.lab456.services.interfaces;

import com.example.lab456.dto.CurrencyDTO;
import com.example.lab456.dto.crupdate.CrupdateCurrencyDTO;

import java.util.List;

public interface DAOCurrencyService {

    List<CurrencyDTO> filterByName(String name);

    CurrencyDTO createAndUpdate(CrupdateCurrencyDTO currencyDTO);
}
