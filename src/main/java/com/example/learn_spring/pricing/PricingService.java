package com.example.learn_spring.pricing;

import java.time.LocalDate;
import java.util.UUID;

public interface PricingService {

    Iterable<Pricing> getAllPrices();

    Pricing getPricingById(UUID id);

    Iterable<Pricing> getPricingByTicker(String ticker);

    Pricing getPricingByTickerAndEffectiveDate(String ticker, LocalDate effectiveDate);

    Pricing getLatestPricingByTicker(String ticker);

    Pricing savePrice(Pricing price);

    Iterable<Pricing> savePrices(Iterable<Pricing> prices);

    void deletePriceById(UUID id);

}
