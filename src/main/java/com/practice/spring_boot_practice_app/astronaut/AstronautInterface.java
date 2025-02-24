package com.practice.spring_boot_practice_app.astronaut;

import com.practice.spring_boot_practice_app.astronaut.dto.AstronautResponse;
import org.springframework.web.service.annotation.GetExchange;

// This can fully replace the service and serviceimpl for simple stuff
public interface AstronautInterface {
    @GetExchange("/astros.json")
    AstronautResponse getResponse();
}
