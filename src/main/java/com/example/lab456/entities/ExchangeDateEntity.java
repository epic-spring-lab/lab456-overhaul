package com.example.lab456.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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

    @Column(name = "date", nullable = false)
    private Date date;

    @OneToMany(mappedBy = "exchangeDate", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ExchangeRateEntity> rates;

}
