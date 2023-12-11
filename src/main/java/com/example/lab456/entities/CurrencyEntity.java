package com.example.lab456.entities;

import jakarta.persistence.*;
import lombok.*;


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

}
