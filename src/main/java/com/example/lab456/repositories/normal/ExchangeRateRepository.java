package com.example.lab456.repositories.normal;

import com.example.lab456.entities.ExchangeRateEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRateEntity, Long> {

    //query example 0
    @Query(value = "select * from rates",
            nativeQuery = true)
    List<ExchangeRateEntity> findAll();

    //query example 1
    @Query("SELECT er from ExchangeRateEntity er where er.exchangeDate.day = ?1 " +
            "and er.exchangeDate.month = ?2 and er.exchangeDate.year = ?3 and er.sourceCurrency.name = ?4")
    List<ExchangeRateEntity> findByDateAndSourceCurrency(Integer day, Integer month, Integer year, String currency);

    //query example 2
    @Modifying
    @Transactional
    @Query(value = "update ExchangeRateEntity set rate = :rate where id = :id")
    void updateRateOnly(@Param("rate") Double rate, @Param("id") Long id);

    // query example 3

//    @Modifying
//    @Transactional
//    @Query(value = "update exchange_rates set rate = :newRate, source_currency_id = :sourceCurrency, target_currency_id = :targetCurrency, exchange_date_id = :dateId where id = :id",
//            nativeQuery = true)
//    void updateRates(@Param("dateId") Long dateId, @Param("sourceCurrency") Long sourceCurrency,
//                     @Param("targetCurrency") Long targetCurrency, @Param("newRate") double newRate, @Param("id") Long id);

    //method for named query
    List<ExchangeRateEntity> findExchangesForDate(@Param("day") Integer day, @Param("month") Integer month, @Param("year") Integer year);

}
