package com.practice.spring_boot_practice_app.astronaut.dto;

import java.util.List;

public record AstronautResponse (String message, Integer number, List<AstronautAssignment>  people) {
    record AstronautAssignment(String name, String craft){}
}


