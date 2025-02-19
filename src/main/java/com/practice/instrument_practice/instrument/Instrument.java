package com.practice.instrument_practice.instrument;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.practice.instrument_practice.pricing.Pricing;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.io.Serializable;
import java.util.Set;
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

    @Formula("(select pricing.price from pricing where pricing.ticker = ticker order by pricing.effective_date desc limit 1)")
    private Double latestPrice;

    @OneToMany(mappedBy = "priceInstrument")
    @JsonManagedReference
    private Set<Pricing> priceHistory;

    @Override
    public String toString(){
        return String.format("Ticker: %s \t, Instrument Name: %s\t, State of Incorporation: %s\t, ID: %s",
                this.ticker, this.instrumentName, this.stateOfIncorporation, this.id);
    }

}
