package com.example.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {

    @Value("${app.environment}")
    private String env;

    @GetMapping("/")
    public String home() {
        return String.format("Hello from %s environment!", env);
    }
    

    @GetMapping("/hello")
    public String hello() {
        TestLombok testLombok = new TestLombok();
        testLombok.setName("Rafi Chandra");
        return String.format("Hello %s!", testLombok.getName());
    }

}