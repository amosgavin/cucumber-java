package com.example.cucumber.stepdefs;

import com.example.cucumber.support.SpringResources;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import org.assertj.core.api.Assertions;
import org.awaitility.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.awaitility.Awaitility.await;

public class AmazonSteps extends SpringResources implements En {

    @After
    public void tearDown() {
        driver.close();
    }

    public AmazonSteps() {
        Given("^a user is on amazon.co.uk$", () -> {
            driver.navigate().to(env.getBaseUrl());
            Assertions
                    .assertThat(driver.getCurrentUrl())
                    .withFailMessage("We should be on <%s> but have reached <%s>", driver.getCurrentUrl(), env.getBaseUrl())
                    .isEqualTo(env.getBaseUrl());
        });
        When("^The user adds a product to cart$", () -> {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            // search for product
            final By twoTabSearchTextBox = By.id("twotabsearchtextbox");
            wait.until(ExpectedConditions.presenceOfElementLocated(twoTabSearchTextBox));
            WebElement elementTwoTabSearchTextBox = driver.findElement(twoTabSearchTextBox);
            elementTwoTabSearchTextBox.sendKeys("AC/DC");
            elementTwoTabSearchTextBox.sendKeys(Keys.RETURN);

            // click on product on PLP
            final By productBackInBlack = By.cssSelector("h2[data-attribute='Back In Black']");
            wait.until(ExpectedConditions
                    .presenceOfElementLocated(productBackInBlack));
            driver.findElement(productBackInBlack).click();

            // add product to basket
            final By buyCdAlbum = By.partialLinkText("Buy CD Album");
            wait.until(ExpectedConditions.presenceOfElementLocated(buyCdAlbum));
            driver.findElement(buyCdAlbum).click();
        });
        And("^Proceeds to checkout$", () -> {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            final By proceedToCheckout = By.partialLinkText("Proceed to checkout");
            wait.until(ExpectedConditions.presenceOfElementLocated(proceedToCheckout));
            driver.findElement(proceedToCheckout).click();
        });
        Then("^The login modal is presented$", () -> await().atMost(Duration.FIVE_SECONDS).untilAsserted(() -> {
            boolean loginPresent = driver.findElement(By.cssSelector("span[class='a-button a-button-span12 a-button-primary']")).isDisplayed();
            Assertions
                    .assertThat(loginPresent)
                    .withFailMessage("The login modal is not present")
                    .isTrue();
        }));
    }
}
