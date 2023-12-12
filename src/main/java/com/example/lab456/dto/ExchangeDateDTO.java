package com.example.lab456.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@Data
public class ExchangeDateDTO {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("day")
        private Integer day;

        @JsonProperty("month")
        private Integer month;

        @JsonProperty("year")
        private Integer year;
}
