package com.svb.qa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class SecureAreaPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By flash = By.id("flash");

    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getFlash() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(flash)).getText();
    }
}
