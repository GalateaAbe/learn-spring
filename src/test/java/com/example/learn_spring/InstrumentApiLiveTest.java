package com.example.learn_spring;

import com.example.learn_spring.instrument.Instrument;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InstrumentApiLiveTest {

    @LocalServerPort
    private int port;
    private String API_ROOT;

    @BeforeEach
    public void setUp(){
        API_ROOT = "http://localhost:" + port + "/api/instruments";
        RestAssured.port = port;
    }

    private Instrument createRandomInstrument(){
        final Instrument instrument = Instrument.builder()
                .ticker(RandomStringUtils.randomAlphabetic(3,5)) // TODO figure out RandomStringGenerator
                .instrumentName(RandomStringUtils.randomAlphabetic(5,16))
                .stateOfIncorporation(RandomStringUtils.randomAlphabetic(2))
                        .build();
        return instrument;
    }

    private String createInstrumentAsUri(Instrument instrument){
        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(instrument)
                .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath().get("id");
    }

    @Test
    public void whenGetAllInstruments_thenOK(){
        final Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
    }

    @Test
    public void whenGetInstrumentById_thenOK(){
        final Instrument instrument = createRandomInstrument();
        final String location = createInstrumentAsUri(instrument);

        final Response response = RestAssured.get(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(instrument.getTicker(),response.jsonPath().get("ticker"));
    }

}
