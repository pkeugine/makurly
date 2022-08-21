package com.makurly.core.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


@RestController()
@RequestMapping("/recommend")
public class WebClientController {

    private final WebClient webClient;

    public WebClientController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/test")
    public String apiTest(){
        return webClient
                .get()
                .uri("/")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
