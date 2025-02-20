package com.practice.spring_boot_practice_app.web;


import com.practice.spring_boot_practice_app.config.SecurityConfig;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(SimpleController.class)
@Import(SecurityConfig.class)
public class SimpleControllerMockMvcTest {

    @Autowired
    private MockMvc mvc;

    @Value("${spring.application.name}")
    String appName;

    @Test
    void autowiringWorked() {
        assertNotNull(mvc);
    }

    @Test
    void testHelloWithoutName() throws Exception {
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andExpect(model().attribute("user", "World"))
                .andExpect(model().attribute("appName", appName));
    }

    @Test
    void testHelloWithName() throws Exception {
        String user = (new RandomStringGenerator.Builder().withinRange('A','Z').build()).generate(5, 15);
        mvc.perform(get("/hello?name={name}", user))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andExpect(model().attribute("user", user))
                .andExpect(model().attribute("appName", appName));
    }

    // TODO Illegal character handling

}