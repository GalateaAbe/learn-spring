package com.example.learn_spring.pricing;

import com.example.learn_spring.instrument.Instrument;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"ticker", "effectiveDate"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pricing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, updatable = false)
    private String ticker;

    @Temporal(TemporalType.DATE)
    @Column(name = "effective_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;

    @Column
    private Double price;

    @Override
    public String toString(){
        return String.format("Effective Date: %tF \t, Ticker: %s\t, ID: %s",
                this.effectiveDate, this.ticker, this.id);
    }

}
