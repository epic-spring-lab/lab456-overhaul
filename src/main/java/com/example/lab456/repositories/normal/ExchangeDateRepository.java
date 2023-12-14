package com.example.lab456.repositories.normal;

import com.example.lab456.entities.ExchangeDateEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExchangeDateRepository extends CrudRepository<ExchangeDateEntity, Long> {
    //query example 5
    @Transactional
    @Query(value = "select * from exchange_dates", nativeQuery = true)
    List<ExchangeDateEntity> findAll();
}
