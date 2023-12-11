package com.example.lab456.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "exchange_rates")
public class ExchangeRateEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exchange_date_id", referencedColumnName = "id", nullable = false)
    private ExchangeDateEntity exchangeDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "source_currency_id", referencedColumnName = "id", nullable = false)
    private CurrencyEntity sourceCurrency;

    @ManyToOne(optional = false)
    @JoinColumn(name = "target_currency_id", referencedColumnName = "id", nullable = false)
    private CurrencyEntity targetCurrency;

    @Column(name = "rate", nullable = false, precision = 10)
    private Double rate;

}
