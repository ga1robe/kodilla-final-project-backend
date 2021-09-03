package com.crud.finalbackend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AdminConfig {
    @Value("${spring.mail.username}")
    private String applicationEmail;

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Value("${weather.api.baseurl}")
    private String weatherApiBaseUrl;
}
