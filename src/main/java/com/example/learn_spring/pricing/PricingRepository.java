package com.example.learn_spring.pricing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.Date;
import java.util.UUID;

public interface PricingRepository extends JpaRepository<Pricing, UUID> {

    Iterable<Pricing> findByTicker(String ticker);

    Pricing findByTickerAndEffectiveDate(String ticker, Date effectiveDate);

    Iterable<Pricing> findByTickerOrderByEffectiveDateDesc(String ticker);
}
