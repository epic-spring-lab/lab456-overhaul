package com.example.lab456.entities;

import com.example.lab456.dto.ExchangeDateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;


@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "exchange_dates")
public class ExchangeDateEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day", nullable = false)
    private Integer day;

    @Column(name = "month", nullable = false)
    private Integer month;

    @Column(name = "year", nullable = false)
    private Integer year;

    @OneToMany(mappedBy = "exchangeDate", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ExchangeRateEntity> rates;

    public ExchangeDateDTO toDto() {
        return ExchangeDateDTO.builder()
                .id(id)
                .day(day)
                .month(month)
                .year(year)
                .build();
    }
}
