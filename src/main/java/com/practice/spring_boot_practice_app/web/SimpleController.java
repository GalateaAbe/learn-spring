package com.practice.spring_boot_practice_app.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;

    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/hello")
    public String helloWorld(Model model, @RequestParam(defaultValue = "World") String name) {
        model.addAttribute("appName", appName);
        model.addAttribute("user", name);
        return "hello";
    }
}
