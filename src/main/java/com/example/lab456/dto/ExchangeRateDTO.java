package com.example.lab456.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@Data
public class ExchangeRateDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("sourceCurrency")
    private CurrencyDTO sourceCurrency;

    @JsonProperty("targetCurrency")
    private CurrencyDTO targetCurrency;

    @JsonProperty("exchangeDate")
    private ExchangeDateDTO exchangeDate;

    @JsonProperty("rate")
    private Double rate;
}
