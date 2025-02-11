package com.example.learn_spring;

import com.example.learn_spring.persistence.model.Instrument;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

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
        final Instrument instrument = new Instrument();
        return instrument;
    }

}
