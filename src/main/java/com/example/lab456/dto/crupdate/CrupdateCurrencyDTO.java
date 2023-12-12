package com.example.lab456.dto.crupdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@Data
public class CrupdateCurrencyDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

}
