package com.example.helloworld.config;

import java.security.PrivateKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwt;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String environment;

    private Jwt jwt = new Jwt();


    private Cors cors = new Cors();

    @Getter
    @Setter
    public static class Jwt {

        private String secret;

        private Long expiration;

        private Long refreshExpiration;

    }

    @Getter
    @Setter
    public static class Cors {
        private List<String> allowedOrigins;
    }

}