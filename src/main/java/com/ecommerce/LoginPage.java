package com.ecommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    // --- LOCATORS (Alamat Elemen) ---
    @FindBy(id = "login2") WebElement loginMenuLink;
    @FindBy(id = "loginusername") WebElement usernameField;
    @FindBy(id = "loginpassword") WebElement passwordField;
    @FindBy(xpath = "//button[text()='Log in']") WebElement loginButton;
    @FindBy(id = "nameofuser") WebElement welcomeText;

    // --- CONSTRUCTOR ---
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // --- ACTIONS (Apa yang dilakukan) ---
    public void loginToApp(String username, String password) throws InterruptedException {
        loginMenuLink.click();
        wait.until(ExpectedConditions.visibilityOf(usernameField)); // Tunggu popup muncul
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        Thread.sleep(3000); // Tunggu login selesai
    }

    public boolean isWelcomeTextDisplayed() {
        try {
            return welcomeText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}