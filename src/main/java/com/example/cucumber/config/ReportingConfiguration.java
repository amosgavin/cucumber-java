package com.example.cucumber.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportingConfiguration {

    @Value("${failureScreenshots}")
    private boolean failureScreenshots;

    public boolean isFailureScreenshots() {
        return failureScreenshots;
    }
}
