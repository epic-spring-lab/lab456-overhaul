package com.example.lab456.services;

import com.example.lab456.dto.ExchangeDateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeDateDTO;

public interface ExchangeDateService {

    Long create(CrupdateExchangeDateDTO exchangeDateDTO);

    ExchangeDateDTO get(Long id);

    void update(Long id, CrupdateExchangeDateDTO exchangeDateDTO);

    void delete(Long id);

}
