package com.example.lab456.mappers;

import com.example.lab456.dto.crupdate.CrupdateExchangeDateDTO;
import com.example.lab456.entities.ExchangeDateEntity;

public class ExchangeDateMapper {
    public static ExchangeDateEntity toEntity(CrupdateExchangeDateDTO crupdateExchangeDateDTO) {
        return ExchangeDateEntity.builder()
                .day(crupdateExchangeDateDTO.getDay())
                .month(crupdateExchangeDateDTO.getMonth())
                .year(crupdateExchangeDateDTO.getYear())
                .build();
    }
}
