package com.practice.spring_boot_practice_app.config;

import com.practice.spring_boot_practice_app.astronaut.AstronautInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AstronautConfig {

    @Bean
    RestTemplate astronautRestTemplate(RestTemplateBuilder builder,
                                       @Value("${astronaut.baseUrl}") String baseUrl){
        return builder.rootUri(baseUrl).build();
    }

    @Bean
    RestTemplate otherRestTemplate(RestTemplateBuilder builder){
        return builder.rootUri("http://api.open-notify.org").build();
    }

    @Bean
    public AstronautInterface astronautInterface (@Value("${astronaut.baseUrl}") String baseUrl){
        WebClient webClient = WebClient.create(baseUrl);
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(AstronautInterface.class);
    }

}
