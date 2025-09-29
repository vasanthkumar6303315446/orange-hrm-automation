package com.vasanth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class AddEmployeePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public AddEmployeePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    
    private By pimMenu = By.xpath("//span[text()='PIM']/ancestor::a");
    private By addEmployeeLink = By.xpath("//a[normalize-space()='Add Employee']");
    private By profilePicInput = By.xpath("//input[@type='file']");
    private By firstNameInput = By.name("firstName");
    private By middleNameInput = By.name("middleName");
    private By lastNameInput = By.name("lastName");
    private By createLoginDetailsToggle = By.xpath("//span[contains(@class,'oxd-switch-input')]");
    private By usernameInput = By.xpath("//label[text()='Username']/following::input[1]");
    private By passwordInput = By.xpath("//label[text()='Password']/following::input[1]");
    private By confirmPasswordInput = By.xpath("//label[text()='Confirm Password']/following::input[1]");
    private By saveButton = By.xpath("//button[@type='submit']");
    private By personalDetailsHeader = By.xpath("//h6[text()='Personal Details']");

    public void goToPIMSection() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
    }

    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeLink)).click();
    }

  
    @SuppressWarnings("deprecation")
	private String downloadImage(String imageUrl) throws IOException {
        Path tempFile = Files.createTempFile("profile", ".jpg");
        try (InputStream in = new URL(imageUrl).openStream()) {
            Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }
        return tempFile.toAbsolutePath().toString();
    }

   
    public void uploadProfilePicture(String imageSource) {
        try {
            String filePath;

           
            if (imageSource.startsWith("http://") || imageSource.startsWith("https://")) {
                filePath = downloadImage(imageSource);
            } else {
                filePath = imageSource;
            }

            WebElement uploadInput = wait.until(ExpectedConditions.presenceOfElementLocated(profilePicInput));
            uploadInput.sendKeys(filePath);

        } catch (IOException e) {
            throw new RuntimeException(" Failed to download image from URL: " + e.getMessage());
        }
    }

    public void enterEmployeeName(String firstName, String middleName, String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        driver.findElement(middleNameInput).sendKeys(middleName);
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enableLoginDetails(String username, String password, String confirmPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(createLoginDetailsToggle)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(confirmPasswordInput).sendKeys(confirmPassword);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalDetailsHeader));
    }

    public boolean isEmployeeAdded() {
        return driver.findElements(personalDetailsHeader).size() > 0;
    }
}
