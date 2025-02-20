package com.practice.spring_boot_practice_app.web;

import com.practice.spring_boot_practice_app.instrument.Instrument;
import com.practice.spring_boot_practice_app.pricing.Pricing;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InstrumentApiLiveTest {

    @LocalServerPort
    private int port;
    private String API_ROOT;

    @BeforeEach
    public void setUp() {
        API_ROOT = "http://localhost:" + port + "/api/instruments";
        RestAssured.port = port;
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

    private String createInstrumentAsUri(Instrument instrument) {
        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(instrument)
                .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath().get("id");
    }

    @Test
    public void testLoggingConfig() {
        log.error("ERROR");
        log.warn("WARNING");
        log.info("INFO");
        log.debug("DEBUG");
        log.trace("TRACE");
        assertEquals(true,true);
    }

    @Test
    public void whenGetAllInstruments_thenOK() {
        final Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetInstrumentById_thenOK() {
        final Instrument instrument = createRandomInstrument();
        final Pricing pricing = createRandomPriceForInstrument(instrument);
        final String location = createInstrumentAsUri(instrument);

        final Response response = RestAssured.get(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(instrument.getTicker(), response.jsonPath().get("ticker"));
        assertEquals(instrument.getInstrumentName(), response.jsonPath().get("instrumentName"));
        assertEquals(instrument.getStateOfIncorporation(), response.jsonPath().get("stateOfIncorporation"));
        assertEquals(instrument.getLatestPrice(), response.jsonPath().get("latestPrice"));

    }

    @Test
    public void whenGetInstrumentByIdNotFound_thenNotFound() {
        final String path = API_ROOT + "/" + UUID.randomUUID();
        final Response response = RestAssured.get(path);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    @Test
    public void whenGetInstrumentByTicker_thenOK() {
        final Instrument instrument = createRandomInstrument();
        final Pricing pricing = createRandomPriceForInstrument(instrument);
        final String path = API_ROOT + "/ticker/" + instrument.getTicker();

        final Response response = RestAssured.get(path);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(instrument.getTicker(), response.jsonPath().get("ticker"));
        assertEquals(instrument.getInstrumentName(), response.jsonPath().get("instrumentName"));
        assertEquals(instrument.getStateOfIncorporation(), response.jsonPath().get("stateOfIncorporation"));
        assertEquals(instrument.getLatestPrice(), response.jsonPath().get("latestPrice"));

    }

    @Test
    public void whenGetInstrumentByTickerNotFound_thenNotFound() {

        final String path = API_ROOT + "/ticker/"
                + (new RandomStringGenerator.Builder()
                .withinRange('A','Z').build()).generate(3,5);
        final Response response = RestAssured.get(path);

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

}
