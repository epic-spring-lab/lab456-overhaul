package com.example.lab456.dto.crupdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@Data
public class CrupdateExchangeDateDTO {

        @JsonProperty("day")
        private Integer day;

        @JsonProperty("month")
        private Integer month;

        @JsonProperty("year")
        private Integer year;
}
