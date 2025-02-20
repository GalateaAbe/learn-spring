package com.practice.spring_boot_practice_app.pricing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface PricingRepository extends JpaRepository<Pricing, UUID> {

    Iterable<Pricing> findByPriceInstrumentTicker(String ticker);

    Pricing findByPriceInstrumentTickerAndEffectiveDate(String ticker, LocalDate effectiveDate);

    Pricing findTopByPriceInstrumentTickerOrderByEffectiveDateDesc(String ticker);

}
