package com.practice.spring_boot_practice_app.astronaut;

import com.practice.spring_boot_practice_app.astronaut.dto.AstronautResponse;
import org.springframework.web.service.annotation.GetExchange;

public interface AstronautService{

    String getPeopleInSpace();

    AstronautResponse getAstronautResponse();

    AstronautResponse getAstronautResponseAsync();
}
