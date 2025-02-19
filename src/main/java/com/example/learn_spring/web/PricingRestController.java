package com.example.learn_spring.web;

import com.example.learn_spring.pricing.Pricing;
import com.example.learn_spring.pricing.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/pricing")
public class PricingRestController {

    @Autowired
    private PricingService pricingService;

    @GetMapping
    public  Iterable<Pricing> getAllPrices(){
        return pricingService.getAllPrices();
    }

    @GetMapping("/{id}")
    public Pricing getPricingById(@PathVariable UUID id) {
        return pricingService.getPricingById(id);
    }

    @GetMapping("/ticker/{ticker}")
    public Iterable<Pricing> getPricingByTicker(@PathVariable String ticker) {
        return pricingService.getPricingByTicker(ticker);
    }

    @GetMapping("/ticker/{ticker}/latest")
    public Pricing getLatestPricingByTicker(@PathVariable String ticker) {
        return pricingService.getLatestPricingByTicker(ticker);
    }

    @GetMapping("/ticker/{ticker}/effectiveDate/{effectiveDate}")
    public Pricing getPricingByTicker(@PathVariable("ticker") String ticker,
                                      @PathVariable("effectiveDate") @DateTimeFormat(pattern = "yyyy-MM-dd")
                                      LocalDate effectiveDate) {
        return pricingService.getPricingByTickerAndEffectiveDate(ticker, effectiveDate);
    }

    @PostMapping
    public Pricing savePrice(@RequestBody Pricing pricing) {
        return pricingService.savePrice(pricing);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<Pricing> savePrices(@RequestBody List<Pricing> pricings) {
        return pricingService.savePrices(pricings);
    }

    @DeleteMapping("/{id}")
    public void deletePrice(@PathVariable UUID id){
        pricingService.getPricingById(id);
        pricingService.deletePriceById(id);
    }

}
