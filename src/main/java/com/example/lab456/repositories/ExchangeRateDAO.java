package com.example.lab456.repositories;

import com.example.lab456.entities.ExchangeRateEntity;

import java.util.List;

public interface ExchangeRateDAO extends CrudDAO<ExchangeRateEntity> {

    List<ExchangeRateEntity> filterBySourceWithPagination(int page, int size, String source);

}
