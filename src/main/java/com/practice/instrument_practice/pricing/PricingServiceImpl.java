package com.practice.instrument_practice.pricing;

import com.practice.instrument_practice.instrument.InstrumentRepository;
import com.practice.instrument_practice.instrument.exception.InstrumentNotFoundException;
import com.practice.instrument_practice.pricing.exception.PricingNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class PricingServiceImpl implements PricingService {

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    InstrumentRepository instrumentRepository;

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
        return pricingRepository.findByPriceInstrumentTicker(ticker);
    }

    @Override
    public Pricing getPricingByTickerAndEffectiveDate(String ticker, LocalDate effectiveDate) {
        log.debug("Finding Price by ticker and date: {} | {}", ticker, effectiveDate);
        return pricingRepository.findByPriceInstrumentTickerAndEffectiveDate(ticker, effectiveDate);
    }

    @Override
    public Pricing getLatestPricingByTicker(String ticker) {
        log.debug("Finding latest Price for ticker: {}", ticker);
        return pricingRepository.findTopByPriceInstrumentTickerOrderByEffectiveDateDesc(ticker);
    }

    @Override
    public Pricing savePrice(PricingRequest pricingRequest) {

        Pricing price = Pricing.builder()
                .effectiveDate(pricingRequest.getEffectiveDate())
                .price(pricingRequest.getPrice())
                .priceInstrument(instrumentRepository
                        .findByTicker(pricingRequest.getTicker())
                        .orElseThrow(InstrumentNotFoundException::new)).build();

        log.debug("Saving Price: {}", pricingRequest);
        return pricingRepository.save(price);
    }

    @Override
    public Iterable<Pricing> savePrices(Iterable<PricingRequest> pricingRequests) {

        List<Pricing> prices = new ArrayList<>();

        for (PricingRequest pricingRequest : pricingRequests) {
            Pricing price = Pricing.builder()
                    .effectiveDate(pricingRequest.getEffectiveDate())
                    .price(pricingRequest.getPrice())
                    .priceInstrument(instrumentRepository
                            .findByTicker(pricingRequest.getTicker())
                            .orElseThrow(InstrumentNotFoundException::new)).build();
            log.trace("Saving Price: {}", price.toString());
            prices.add(price);
        }

        log.debug("Saving a list of {} Prices", pricingRequests.spliterator().getExactSizeIfKnown());
        return pricingRepository.saveAll(prices);
    }

    @Override
    public void deletePriceById(UUID id) {
        log.debug("Deleting Price with id: {}", id);
        pricingRepository.findById(id).orElseThrow(PricingNotFoundException::new);
        pricingRepository.deleteById(id);
    }
}
