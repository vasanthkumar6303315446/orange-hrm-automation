package com.vasanth.tests;

import org.testng.annotations.*;
import com.vasanth.pages.LoginPage;
import com.vasanth.base.BaseTest;
import com.vasanth.pages.AdminPage;

public class SystemUsersTest extends BaseTest {

    LoginPage loginPage;
    AdminPage adminPage;

    @BeforeClass
    public void pageSetup() {
        loginPage = new LoginPage(driver, wait);
        adminPage = new AdminPage(driver, wait);
    }

    @Test(priority = 1)
    public void loginTest() {
        loginPage.login("Admin", "admin123");
    }

    @Test(priority = 2, dependsOnMethods = "loginTest")
    public void createUserTest() {
        adminPage.openAdminPage();
        adminPage.createUser("vasanth.new", "Admin", "Orange Test", "Disabled", "Test@1234");
    }
}

