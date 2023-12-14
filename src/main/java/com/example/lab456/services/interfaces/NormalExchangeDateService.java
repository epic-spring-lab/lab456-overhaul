package com.example.lab456.services.interfaces;

import com.example.lab456.entities.ExchangeDateEntity;

import java.util.List;

public interface NormalExchangeDateService {

    void deleteAll();

    List<ExchangeDateEntity> createAll(List<ExchangeDateEntity> dateEntities);

    List<ExchangeDateEntity> getAll();
}
