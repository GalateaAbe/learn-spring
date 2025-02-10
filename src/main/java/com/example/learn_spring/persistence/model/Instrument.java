package com.example.learn_spring.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table()
@Getter @Setter @NoArgsConstructor
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String ticker;

    @Column(name = "instrument_name", nullable = false)
    private String instrumentName;

    @Column(name = "state_of_incorporation", length=2)
    private String stateOfIncorporation;

}
