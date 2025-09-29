package com.vasanth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {
    WebDriver driver;
    WebDriverWait wait;

    public ForgotPasswordPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By forgotPasswordLink = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']");
    private By usernameField = By.xpath("//input[@placeholder='Username']");
    private By resetButton = By.xpath("//button[@type='submit']");
    private By confirmationMessage = By.xpath("//h6[contains(text(),'Reset Password link sent')]");

   
    public void clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
    }

    
    public void resetPassword(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(resetButton)).click();
    }

    public boolean isResetConfirmationDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage)).isDisplayed();
    }
}
