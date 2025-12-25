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

    // [UPDATE] Locator tombol login lebih spesifik agar tidak tertukar tombol lain
    @FindBy(xpath = "//button[@onclick='logIn()']") WebElement loginButton;

    @FindBy(id = "nameofuser") WebElement welcomeText;

    // --- CONSTRUCTOR ---
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Perpanjang sedikit durasi wait
        PageFactory.initElements(driver, this);
    }

    // --- ACTIONS ---
    public void loginToApp(String username, String password) {
        // Klik menu Login di navbar
        loginMenuLink.click();

        // Tunggu modal muncul dan field username bisa diketik
        wait.until(ExpectedConditions.visibilityOf(usernameField));

        usernameField.clear(); // [BEST PRACTICE] Bersihkan dulu takutnya ada sisa teks
        usernameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);

        // Klik tombol Log in di dalam modal
        loginButton.click();

        // [HAPUS Thread.sleep]
        // Kita tidak butuh sleep di sini karena kita akan melakukan waiting di langkah validasi (isWelcomeTextDisplayed)
    }

    // [BAGIAN KRUSIAL YANG DIPERBAIKI]
    public boolean isWelcomeTextDisplayed() {
        try {
            // Tunggu sampai teks "Welcome..." benar-benar muncul sebelum return true
            // Kalau dalam 15 detik tidak muncul, dia akan throw TimeoutException -> masuk catch -> return false
            wait.until(ExpectedConditions.visibilityOf(welcomeText));
            return welcomeText.isDisplayed();
        } catch (Exception e) {
            // Jika waktu habis dan elemen tidak muncul
            return false;
        }
    }
}