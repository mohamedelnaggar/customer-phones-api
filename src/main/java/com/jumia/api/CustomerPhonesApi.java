package com.jumia.api;

import com.jumia.api.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class CustomerPhonesApi {
    public static void main(String[] args) {
        SpringApplication.run(CustomerPhonesApi.class, args);
    }
}
