package com.svb.qa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By user = By.id("username");
    private final By pass = By.id("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By flash = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public LoginPage open() {
        driver.get("https://the-internet.herokuapp.com/login");
        return this;
    }

    public SecureAreaPage loginAs(String username, String password) {
        driver.findElement(user).clear();
        driver.findElement(user).sendKeys(username);
        driver.findElement(pass).clear();
        driver.findElement(pass).sendKeys(password);
        driver.findElement(loginBtn).click();
        wait.until(ExpectedConditions.urlContains("/secure"));
        return new SecureAreaPage(driver);
    }

    public String getFlash() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(flash)).getText();
    }
}
