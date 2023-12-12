package com.example.lab456.dto.crupdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@Data
public class CrupdateExchangeRateDTO {

    @JsonProperty("sourceCurrencyId")
    private Long sourceCurrencyId;

    @JsonProperty("targetCurrencyId")
    private Long targetCurrencyId;

    @JsonProperty("exchangeDateId")
    private Long exchangeDateId;

    @JsonProperty("rate")
    private Double rate;
}
