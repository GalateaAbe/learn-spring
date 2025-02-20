package com.practice.spring_boot_practice_app.web;

import com.practice.spring_boot_practice_app.instrument.Instrument;
import com.practice.spring_boot_practice_app.pricing.Pricing;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InstrumentRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void autowiringWorked(){
        assertNotNull(restTemplate);
    }

    private Instrument createRandomInstrument() {

        return Instrument.builder()
                .ticker((new RandomStringGenerator.Builder().withinRange('A', 'Z').build()).generate(3, 5))
                .instrumentName((new RandomStringGenerator.Builder().withinRange('A', 'Z').build()).generate(6, 20))
                .stateOfIncorporation((new RandomStringGenerator.Builder().withinRange('A', 'Z').build()).generate(2))
                .build();
    }

    private Pricing createRandomPriceForInstrument(Instrument instrument) {
        return Pricing.builder()
                .priceInstrument(instrument)
                .effectiveDate(LocalDate.ofEpochDay(ThreadLocalRandom.current().nextInt(3650)))
                .price(ThreadLocalRandom.current().nextDouble(0, 9999.9))
                .build();
    }


    @Test
    void testEmptyGetAllInstrumentsById_thenOk() {
        List<Instrument> instruments = restTemplate.getForObject("/api/instruments",Instrument.class);
    }

}
