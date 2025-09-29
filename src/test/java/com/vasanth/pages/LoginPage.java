package com.vasanth.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private  By usernameField = By.xpath("//input[@placeholder='Username']");
    private  By passwordField = By.xpath("//input[@placeholder='Password']");
    private  By loginButton = By.xpath("//button[@type='submit']");
    private  By userDropdown = By.xpath("//p[@class='oxd-userdropdown-name']");

    
    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

  
    public boolean isLoginSuccessful() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(userDropdown)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}

