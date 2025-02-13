package com.example.learn_spring.pricing;

import com.example.learn_spring.web.exception.pricing.PricingNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class PricingServiceImpl implements PricingService {

    @Autowired
    private PricingRepository pricingRepository;

    @Override
    public Iterable<Pricing> getAllPrices() {
        return pricingRepository.findAll();
    }

    @Override
    public Pricing getPricingById(UUID id) {
        return pricingRepository.findById(id).orElseThrow(PricingNotFoundException::new);
    }

    @Override
    public Iterable<Pricing> getPricingByTicker(String ticker) {
        return pricingRepository.findByTicker(ticker);
    }

    @Override
    public Pricing getPricingByTickerAndEffectiveDate(String ticker, Date effectiveDate) {
        return pricingRepository.findByTickerAndEffectiveDate(ticker, effectiveDate);
    }

    @Override
    public Pricing getLatestPricingByTicker(String ticker) {
        return pricingRepository.findByTickerOrderByEffectiveDateDesc(ticker).iterator().next();
    }

    @Override
    public Pricing savePrice(Pricing price) {
        return pricingRepository.save(price);
    }

    @Override
    public Iterable<Pricing> savePrices(Iterable<Pricing> prices) {
        return pricingRepository.saveAll(prices);
    }

    @Override
    public void deletePriceById(UUID id) {
        pricingRepository.deleteById(id);
    }
}
