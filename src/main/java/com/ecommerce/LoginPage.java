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

    // --- LOCATORS ---
    @FindBy(id = "login2") WebElement loginMenuLink;
    @FindBy(id = "loginusername") WebElement usernameField;
    @FindBy(id = "loginpassword") WebElement passwordField;
    @FindBy(xpath = "//button[@onclick='logIn()']") WebElement loginButton;
    @FindBy(id = "nameofuser") WebElement welcomeText;

    // --- CONSTRUCTOR ---
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // --- ACTIONS ---
    public void loginToApp(String username, String password) {
        // Klik menu Login di navbar
        loginMenuLink.click();

        // Tunggu form login muncul dan field username bisa diketik
        wait.until(ExpectedConditions.visibilityOf(usernameField));

        usernameField.clear();
        usernameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);

        // Klik tombol Log in di dalam modal
        loginButton.click();
    }

    public boolean isWelcomeTextDisplayed() {
        try {
            // Tunggu sampai teks "Welcome..." benar-benar muncul sehingga return true
            // Jika dalam 15 detik tidak muncul, maka akan throw TimeoutException -> masuk catch -> return false
            wait.until(ExpectedConditions.visibilityOf(welcomeText));
            return welcomeText.isDisplayed();
        } catch (Exception e) {
            // Jika waktu habis dan elemen tidak muncul
            return false;
        }
    }
}