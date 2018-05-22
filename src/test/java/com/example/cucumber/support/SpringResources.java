package com.example.cucumber.support;

import com.example.cucumber.CucumberApplication;
import com.example.cucumber.config.EnvConfiguration;
import com.example.cucumber.config.ReportingConfiguration;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = CucumberApplication.class)
public class SpringResources {

    @Resource
    protected WebDriver driver;
    @Resource
    protected EnvConfiguration env;
    @Resource
    protected ReportingConfiguration reporting;
}
