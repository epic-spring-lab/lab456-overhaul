package com.example.lab456.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "currencies")
public class CurrencyEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @OneToMany(mappedBy = "sourceCurrency", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ExchangeRateEntity> sourceRates;

    @OneToMany(mappedBy = "targetCurrency", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ExchangeRateEntity> targetRates;

}
