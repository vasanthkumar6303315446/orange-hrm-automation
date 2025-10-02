package com.vasanth.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.vasanth.base.BaseTest;
import com.vasanth.pages.AddEmployeePage;
import com.vasanth.pages.LoginPage;

public class AddEmployeeTest extends BaseTest {
    private AddEmployeePage addEmployeePage;
    private LoginPage loginPage;

    @BeforeClass
    public void pageSetup() {
        loginPage = new LoginPage(driver, wait);
        addEmployeePage = new AddEmployeePage(driver, wait);
    }

    @Test(priority = 1)
    public void loginTest() {
        loginPage.login("Admin", "admin123");
    }

    @Test(priority = 2)
    public void testAddNewEmployee() {
        addEmployeePage.goToPIMSection();
        addEmployeePage.clickAddEmployee();

        
        addEmployeePage.uploadProfilePicture("https://www.bing.com/th/id/OIP.bmNx4HcklMqp-k11aTDbkQHaE8?w=242");

        addEmployeePage.enterEmployeeName("vasan", "k.", "katikuti");
        addEmployeePage.enableLoginDetails("vasan.kattikuti", "SecurePass123", "SecurePass123");
        addEmployeePage.clickSave();

        Assert.assertTrue(addEmployeePage.isEmployeeAdded(), " Employee was not added successfully.");
    }
}
