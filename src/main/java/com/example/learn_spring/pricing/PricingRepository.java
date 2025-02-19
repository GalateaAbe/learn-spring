package com.example.learn_spring.pricing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public interface PricingRepository extends JpaRepository<Pricing, UUID> {

    Iterable<Pricing> findByTicker(String ticker);

    Pricing findByTickerAndEffectiveDate(String ticker, LocalDate effectiveDate);

    Pricing findTopByTickerOrderByEffectiveDateDesc(String ticker);

}
