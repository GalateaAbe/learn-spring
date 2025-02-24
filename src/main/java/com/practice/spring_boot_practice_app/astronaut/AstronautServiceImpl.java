package com.practice.spring_boot_practice_app.astronaut;

import com.practice.spring_boot_practice_app.astronaut.dto.AstronautResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AstronautServiceImpl implements AstronautService {

    private final RestTemplate template;
    private final WebClient client;

    public AstronautServiceImpl(@Qualifier("astronautRestTemplate") RestTemplate template){
        this.template = template;
        this.client = WebClient.create("http://api.open-notify.org");
    }

    @Override
    public String getPeopleInSpace() {
        return this.template.getForObject("/astros.json", String.class);
    }

    @Override
    public AstronautResponse getAstronautResponse() {
        return this.template.getForObject("/astros.json", AstronautResponse.class);
    }

    @Override
    public AstronautResponse getAstronautResponseAsync() {
        return this.client.get()
                .uri("/astros.json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AstronautResponse.class)
                .block();
    }
}
