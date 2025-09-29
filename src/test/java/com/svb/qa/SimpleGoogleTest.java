package com.svb.qa;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.List;

public class SimpleGoogleTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        driver = new FirefoxDriver(options); // Selenium Manager resolves geckodriver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    void googleSearch_printFirstFiveResults() {
        driver.get("https://www.google.com/");
        WebElement box = driver.findElement(By.name("q"));
        box.sendKeys("Selenium WebDriver");
        box.sendKeys(Keys.ENTER);

        List<WebElement> results = driver.findElements(By.cssSelector("h3"));
        int limit = Math.min(results.size(), 5);
        for (int i = 0; i < limit; i++) {
            System.out.println((i + 1) + ". " + results.get(i).getText());
        }
        Assertions.assertTrue(results.size() > 0, "Expected at least one search result");
    }
}
