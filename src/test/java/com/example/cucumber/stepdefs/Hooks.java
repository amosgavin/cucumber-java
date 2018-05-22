package com.example.cucumber.stepdefs;

import com.example.cucumber.support.SpringResources;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class Hooks extends SpringResources {

    @After
    public void takeTestFailureScreenshots(Scenario scenario) {
        boolean failureScreenshots = reporting.isFailureScreenshots();
        if (failureScreenshots && scenario.isFailed()){
//            TODO: completed this
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        }
    }
}
