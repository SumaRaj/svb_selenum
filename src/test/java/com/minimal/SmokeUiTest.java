package com.minimal;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

class SmokeUiTest {
    private WebDriver driver;

    @BeforeEach
    void openBrowser() {
        FirefoxOptions opts = new FirefoxOptions();
        opts.addArguments("-headless");
        driver = new FirefoxDriver(opts); // Selenium Manager auto-resolves geckodriver
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) driver.quit();
    }

    @Test
    void seleniumSiteTitleContainsSelenium() {
        driver.get("https://www.selenium.dev/");
        Assertions.assertTrue(driver.getTitle().toLowerCase().contains("selenium"));
    }
}
