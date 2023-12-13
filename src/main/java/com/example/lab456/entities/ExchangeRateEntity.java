package com.example.lab456.entities;

import com.example.lab456.dto.ExchangeRateDTO;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "exchange_rates")
@NamedQuery(
        name = "ExchangeRateEntity.findExchangesForDate",
        query = "SELECT er FROM ExchangeRateEntity er where er.exchangeDate.day = :day and er.exchangeDate.month = :month and er.exchangeDate.year = :year"
)
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

    public ExchangeRateDTO toDto() {
        return ExchangeRateDTO.builder()
                .id(id)
                .exchangeDate(exchangeDate.toDto())
                .sourceCurrency(sourceCurrency.toDto())
                .targetCurrency(targetCurrency.toDto())
                .rate(rate)
                .build();
    }

}
