package com.jumia.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private String[] whiteListDomains;

    public String[] getWhiteListDomains() {
        return whiteListDomains;
    }

    public void setWhiteListDomains(String[] whiteListDomains) {
        this.whiteListDomains = whiteListDomains;
    }
}
