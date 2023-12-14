package com.example.lab456.mappers;

import com.example.lab456.dto.crupdate.CrupdateCurrencyDTO;
import com.example.lab456.entities.CurrencyEntity;

public class CurrencyMapper {
    public static CurrencyEntity toEntity(CrupdateCurrencyDTO currencyDTO) {
        return CurrencyEntity.builder()
                .name(currencyDTO.getName())
                .code(currencyDTO.getCode())
                .build();
    }
}
