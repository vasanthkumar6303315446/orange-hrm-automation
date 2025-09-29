package com.vasanth.tests;

import com.vasanth.base.BaseTest;
import com.vasanth.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private boolean loginFailed = false;

    @BeforeClass
    public void setupPages() {
        loginPage = new LoginPage(driver, wait);
    }

    @Test(priority = 1)
    public void loginWithValidCredentials() {
        loginPage.login("Admin", "admin123");
        loginFailed = !loginPage.isLoginSuccessful();
        System.out.println("Login with valid credentials: " + (loginFailed ? "Failed" : "Success"));
    }

    @Test(priority = 2)
    public void loginWithInvalidCredentials() {
        if (!loginFailed) {
            System.out.println("Skipping invalid login test because valid login succeeded.");
            return; 
        }
        loginPage.login("InvalidUser", "WrongPass");
        Assert.assertTrue(driver.getPageSource().contains("Invalid credentials"), "Error message not displayed!");
    }
}
