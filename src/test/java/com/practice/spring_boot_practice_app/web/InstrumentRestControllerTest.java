package com.practice.spring_boot_practice_app.web;

import com.practice.spring_boot_practice_app.instrument.Instrument;
import com.practice.spring_boot_practice_app.pricing.Pricing;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InstrumentRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    String API_ROOT = "api/instruments";

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
    void autowiringWorked() {
        assertNotNull(restTemplate);
    }

    @Test
    void testSayHelloWithoutName() {
        Greeting greeting = restTemplate.getForObject(API_ROOT + "/hello", Greeting.class);
        assertEquals("Hello, World!", greeting.message());
    }

    @Test
    void testSayHelloWithName() {
        String user = (new RandomStringGenerator.Builder().withinRange('A', 'Z').build()).generate(5, 15);
        ResponseEntity<Greeting> response = restTemplate.getForEntity(
                API_ROOT + "/hello?name={name}", Greeting.class, user);
        assertAll(
                () -> assertTrue(response.getStatusCode().is2xxSuccessful()),
                () -> assertEquals(MediaType.APPLICATION_JSON,
                        response.getHeaders().getContentType()),
                () -> assertEquals((String.format("Hello, %s!", user)),
                        Objects.requireNonNull(response.getBody()).message())
        );
    }

    @Test
    void testEmptyGetAllInstrumentsById_thenOk() {
        ResponseEntity<Instrument[]> response = restTemplate.getForEntity(API_ROOT, Instrument[].class);
        assertAll(
                () -> assertTrue(response.getStatusCode().is2xxSuccessful())
        );
    }

    @Test
    void testGetAllInstrumentsById_thenOk() {
        ResponseEntity<Instrument[]> response = restTemplate.getForEntity(API_ROOT, Instrument[].class);
        assertAll(
                () -> assertTrue(response.getStatusCode().is2xxSuccessful())
        );
    }

}
