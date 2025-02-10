package com.example.learn_spring.persistence.model;

import jakarta.persistence.*;

@Entity
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String ticker;

    @Column(nullable = false)
    private String instrumentName;

    @Column
    private String stateOfIncorporation;

}
