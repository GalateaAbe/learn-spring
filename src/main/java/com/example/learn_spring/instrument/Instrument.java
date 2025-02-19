package com.example.learn_spring.instrument;

import com.example.learn_spring.pricing.Pricing;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instrument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, updatable = false)
    private String ticker;

    @Column(name = "instrument_name", nullable = false)
    private String instrumentName;

    @Column(name = "state_of_incorporation", length = 2)
    private String stateOfIncorporation;

    @Override
    public String toString(){
        return String.format("Ticker: %s \t, Instrument Name: %s\t, State of Incorporation: %s\t, ID: %s",
                this.ticker, this.instrumentName, this.stateOfIncorporation, this.id);
    }

}
