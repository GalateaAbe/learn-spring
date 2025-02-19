package com.example.learn_spring.logcheck;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/log")
public class LogCheckRestController {

    @GetMapping
    public String logCheck(){
        log.trace("Trace posted");
        log.debug("Debug posted");
        log.info("Info posted");
        log.warn("Warning posted");
        log.error("Error posted");
        return "Logs posted to console";
    }
}
