package com.example.lab456.dao;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class ExchangeDateDAO {

    private long id;

    private Integer day;

    private Integer month;

    private Integer year;
}
