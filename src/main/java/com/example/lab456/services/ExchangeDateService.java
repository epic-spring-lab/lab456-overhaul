package com.example.lab456.services;

import com.example.lab456.dto.ExchangeDateDTO;
import com.example.lab456.dto.crupdate.CrupdateExchangeDateDTO;
import com.example.lab456.entities.ExchangeDateEntity;

import java.util.List;

public interface ExchangeDateService {

    Long create(CrupdateExchangeDateDTO exchangeDateDTO);

    ExchangeDateDTO get(Long id);

    void update(Long id, CrupdateExchangeDateDTO exchangeDateDTO);

    void delete(Long id);

    void deleteAll();

    List<ExchangeDateEntity> createAll(List<ExchangeDateEntity> dateEntities);

    List<ExchangeDateEntity> getAll();
}
