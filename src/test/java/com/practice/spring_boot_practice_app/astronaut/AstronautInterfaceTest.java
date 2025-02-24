package com.practice.spring_boot_practice_app.astronaut;

import com.practice.spring_boot_practice_app.astronaut.dto.AstronautResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AstronautInterfaceTest {

    @Autowired
    private AstronautInterface astronautInterface;

    @Test
    void getResponse() {
        AstronautResponse response = astronautInterface.getResponse();
        assertAll(
                () -> assertEquals("success", response.message()),
                () -> assertTrue(response.number() >= 0),
                () -> assertEquals(response.people().size(), response.number())
        );
        log.trace(response.toString());
    }
}