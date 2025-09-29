package com.svb.qa;

import com.svb.qa.pages.LoginPage;
import com.svb.qa.pages.SecureAreaPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginFlowPomTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        driver = new FirefoxDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    void login_success_messageShown() {
        LoginPage login = new LoginPage(driver).open();
        SecureAreaPage secure = login.loginAs("tomsmith", "SuperSecretPassword!");
        String msg = secure.getFlash();
        assertTrue(msg.contains("You logged into a secure area!"));
    }
}
