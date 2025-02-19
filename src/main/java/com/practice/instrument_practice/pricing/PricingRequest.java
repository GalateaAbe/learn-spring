package com.practice.instrument_practice.pricing;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PricingRequest {

    private String ticker;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;

    private Double price;
}
