package com.practice.spring_boot_practice_app.astronaut;

import com.practice.spring_boot_practice_app.astronaut.dto.AstronautResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class AstronautServiceImplTest {

    @Autowired
    private AstronautService astronautService;

    @Test
    void getPeopleInSpace() {
        String people = astronautService.getPeopleInSpace();
        assertTrue(people.contains("people"));
        log.trace(people);
    }

    @Test
    void getAstronautResponse() {
        AstronautResponse response = astronautService.getAstronautResponse();
        assertAll(
                () -> assertEquals("success", response.message()),
                () -> assertTrue(response.number() >= 0),
                () -> assertEquals(response.people().size(), response.number())
        );
        log.trace(response.toString());
    }

    @Test
    void getAstronautResponseAsync() {
        AstronautResponse response = astronautService.getAstronautResponseAsync();
        assertAll(
                () -> assertEquals("success", response.message()),
                () -> assertTrue(response.number() >= 0),
                () -> assertEquals(response.people().size(), response.number())
        );
        log.trace(response.toString());
    }
}