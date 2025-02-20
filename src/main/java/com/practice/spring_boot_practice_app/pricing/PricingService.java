package com.practice.spring_boot_practice_app.pricing;

import java.time.LocalDate;
import java.util.UUID;

public interface PricingService {

    Iterable<Pricing> getAllPrices();

    Pricing getPricingById(UUID id);

    Iterable<Pricing> getPricingByTicker(String ticker);

    Pricing getPricingByTickerAndEffectiveDate(String ticker, LocalDate effectiveDate);

    Pricing getLatestPricingByTicker(String ticker);

    Pricing savePrice(PricingRequest pricingRequest);

    Iterable<Pricing> savePrices(Iterable<PricingRequest> pricingRequests);

    void deletePriceById(UUID id);

}
