package com.example.lab456.repositories;

import com.example.lab456.entities.ExchangeDateEntity;

import java.util.List;

public interface ExchangeDateDAO extends CrudDAO<ExchangeDateEntity> {

    List<ExchangeDateEntity> findEvenDatesForYear(int year);
}
