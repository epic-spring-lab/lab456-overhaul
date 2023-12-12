package com.example.lab456.dao;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class CurrencyDAO {

    private long id;
    private String name;
    private String code;

}
