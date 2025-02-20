package com.practice.spring_boot_practice_app.web;

import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.jupiter.api.Assertions.*;

class SimpleControllerUnitTest {

    @Test
    void testHelloWorld() {
        SimpleController controller = new SimpleController();
        Model model = new BindingAwareModelMap();
        String user = (new RandomStringGenerator.Builder().withinRange('A','Z').build()).generate(5, 15);
        String result = controller.helloWorld(model, user);
        assertAll(
                () -> assertEquals("hello", result),
                () -> assertEquals(user, model.getAttribute("user"))
        );
    }
}