package com.example.lab456.repositories;

import com.example.lab456.entities.CurrencyEntity;

import java.util.List;


public interface CurrencyDAO extends CrudDAO<CurrencyEntity> {

    List<CurrencyEntity> filterByName(String name);

}
