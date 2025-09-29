package com.vasanth.tests;

import com.vasanth.base.BaseTest;
import com.vasanth.pages.ForgotPasswordPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ForgotPasswordFlowTest extends BaseTest {

    private ForgotPasswordPage forgotPasswordPage;

    @BeforeClass
    public void setupPages() {
        forgotPasswordPage = new ForgotPasswordPage(driver, wait);
    }

    @Test
    public void verifyForgotPasswordFlow() {
        forgotPasswordPage.clickForgotPasswordLink();
        forgotPasswordPage.resetPassword("Abhishek Savani");
        // You can add assertion here
        // Assert.assertTrue(forgotPasswordPage.isResetConfirmationDisplayed());
    }
}
