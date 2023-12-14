package com.example.lab456.services.interfaces;

import com.example.lab456.dto.ExchangeRateDTO;

import java.util.List;

public interface DAOExchangeRateService {

    List<ExchangeRateDTO> filterBySourceWithPagination(int page, int size, String source);
}
