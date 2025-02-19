package com.example.learn_spring.pricing;

import com.example.learn_spring.pricing.exception.PricingNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class PricingServiceImpl implements PricingService {

    @Autowired
    private PricingRepository pricingRepository;

    @Override
    public Iterable<Pricing> getAllPrices() {
        log.debug("Finding all Prices");
        return pricingRepository.findAll();
    }

    @Override
    public Pricing getPricingById(UUID id) {
        log.debug("Finding Price by id: {}", id);
        return pricingRepository.findById(id).orElseThrow(PricingNotFoundException::new);
    }

    @Override
    public Iterable<Pricing> getPricingByTicker(String ticker) {
        log.debug("Finding Prices by ticker: {}", ticker);
        return pricingRepository.findByTicker(ticker);
    }

    @Override
    public Pricing getPricingByTickerAndEffectiveDate(String ticker, LocalDate effectiveDate) {
        log.debug("Finding Price by ticker and date: {} | {}", ticker, effectiveDate);
        return pricingRepository.findByTickerAndEffectiveDate(ticker, effectiveDate);
    }

    @Override
    public Pricing getLatestPricingByTicker(String ticker) {
        log.debug("Finding latest Price for ticker: {}", ticker);
        return pricingRepository.findTopByTickerOrderByEffectiveDateDesc(ticker);
//        return pricingRepository.findByTickerOrderByEffectiveDateDesc(ticker).iterator().next();
    }

    @Override
    public Pricing savePrice(Pricing price) {
        log.debug("Saving Price: {}", price);
        return pricingRepository.save(price);
    }

    @Override
    public Iterable<Pricing> savePrices(Iterable<Pricing> prices) {
        log.debug("Saving a list of {} Prices", prices.spliterator().getExactSizeIfKnown());
        for (Pricing price: prices) {
            log.trace(price.toString());
        }

        return pricingRepository.saveAll(prices);
    }

    @Override
    public void deletePriceById(UUID id) {
        log.debug("Deleting Price with id: {}", id);
        pricingRepository.findById(id).orElseThrow(PricingNotFoundException::new);
        pricingRepository.deleteById(id);
    }
}
