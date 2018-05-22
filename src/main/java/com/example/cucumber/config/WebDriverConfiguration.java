package com.example.cucumber.config;

import com.google.common.io.Resources;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.Config;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.List;

@Configuration
public class WebDriverConfiguration {

    private enum BROWSER_TYPE {
        CHROME, FIREFOX
    }

    @Value("${local.browser}")
    private BROWSER_TYPE browser;
    @Value("${global.chromeDriverVersion}")
    private String chromeDriverVersion;

    /**
     * Downloads driver binary and initialises the driver. Binary is downloaded
     * to build output. If it already downloaded then the cached binary is used.
     *
     * @throws IllegalStateException if the correct browser has not been defined in Application.yml
     */
    @Bean
    public WebDriver binaryDownloadAndInitDriver() {
        URL driverResource = Resources.getResource("drivers/");
        WebDriverManager.config().setTargetPath(driverResource.getPath());
        File[] files = new File(driverResource.getPath()).listFiles();
        assert files != null;
        boolean binaryExists = binaryExists(files);

        if (BROWSER_TYPE.CHROME.equals(browser)) {
            if (binaryExists) {
                ChromeDriverManager.getInstance().forceCache();
            } else {
                ChromeDriverManager.getInstance().version(chromeDriverVersion).setup();
            }
            return new ChromeDriver();
        } else if (BROWSER_TYPE.FIREFOX.equals(browser)) {
            WebDriverManager driverManager = FirefoxDriverManager.getInstance();
            if (binaryExists) {
                driverManager.forceCache();
            } else {
                driverManager.setup();
            }
            System.setProperty("webdriver.firefox.marionette", driverManager.getBinaryPath());
            return new FirefoxDriver();
        } else {
            throw new IllegalStateException("Need to specify a valid browser type");
        }
    }

    private boolean binaryExists(File[] files) {
        boolean binaryExists = false;
        for (File file : files) {
            if (file.getName().contains(browser.toString().toLowerCase())) {
                binaryExists = true;
                break;
            }
        }
        return binaryExists;
    }
}
