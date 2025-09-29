package com.vasanth.pages;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AdminPage {
    WebDriver driver;
    WebDriverWait wait;

    public AdminPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

  private   By adminMenu = By.xpath("//span[text()='Admin']");
  private   By addButton = By.xpath("//button[normalize-space()='Add']");
  private   By usernameField = By.xpath("//label[text()='Username']/following::input[1]");
  private   By userRoleDropdown = By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-text')]");
  private   By employeeNameField = By.xpath("//input[@placeholder='Type for hints...']");
  private   By statusDropdown = By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text')]");
  private   By passwordField = By.xpath("//label[text()='Password']/following::input[1]");
  private   By confirmPasswordField = By.xpath("//label[text()='Confirm Password']/following::input[1]");
  private   By saveButton = By.xpath("//button[@type='submit']");
  
    public void openAdminPage() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }
    
    public void createUser(String username, String role, String employee, String status, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);

        wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();
        selectFromDropdown("//div[@role='listbox']//span", role);

        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameField)).sendKeys(employee);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']//span"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
        selectFromDropdown("//div[@role='listbox']//span", status);

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField)).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    private void selectFromDropdown(String xpath, String text) {
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(text)) {
                option.click();
                break;
            }
        }
    }
}

