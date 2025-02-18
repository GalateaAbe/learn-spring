package com.example.learn_spring.pricing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.UUID;

public interface PricingRepository extends JpaRepository<Pricing, UUID> {

    Iterable<Pricing> findByTicker(String ticker);

    Pricing findByTickerAndEffectiveDate(String ticker, Date effectiveDate);

    Iterable<Pricing> findByTickerOrderByEffectiveDateDesc(String ticker);
}
