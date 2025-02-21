package com.practice.spring_boot_practice_app.astronaut;

import com.practice.spring_boot_practice_app.astronaut.dto.AstronautResponse;

public interface AstronautService{

    String getPeopleInSpace();

    AstronautResponse getAstronautResponse();

    AstronautResponse getAstronautResponseAsync();
}
