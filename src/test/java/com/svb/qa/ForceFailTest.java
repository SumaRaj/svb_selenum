package com.svb.qa;

import com.svb.qa.util.ScreenshotOnFailureExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;

@ExtendWith(ScreenshotOnFailureExtension.class)
public class ForceFailTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        FirefoxOptions opts = new FirefoxOptions();
        opts.addArguments("-headless");
        driver = new FirefoxDriver(opts);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    void intentionallyFails() {
        driver.get("https://www.selenium.dev/");
        Assertions.assertTrue(driver.getTitle().contains("TotallyWrongTitle"));
    }
}
