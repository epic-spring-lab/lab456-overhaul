package com.example.lab456.services.interfaces;

import com.example.lab456.dto.ExchangeDateDTO;

import java.util.List;

public interface DAOExchangeDateService {

    List<ExchangeDateDTO> findEvenDatesForYear(int year);
}
