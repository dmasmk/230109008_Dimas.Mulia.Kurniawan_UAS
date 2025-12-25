package com.ecommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    // --- LOCATORS (DAFTAR ALAMAT TOMBOL) ---
    @FindBy(xpath = "//button[text()='Place Order']") WebElement placeOrderButton;

    // Form Input
    @FindBy(id = "name") WebElement nameField;
    @FindBy(id = "country") WebElement countryField;
    @FindBy(id = "city") WebElement cityField;
    @FindBy(id = "card") WebElement cardField;
    @FindBy(id = "month") WebElement monthField;
    @FindBy(id = "year") WebElement yearField;

    @FindBy(xpath = "//button[text()='Purchase']") WebElement purchaseButton;
    @FindBy(xpath = "//h2[text()='Thank you for your purchase!']") WebElement successMessage;

    // --- CONSTRUCTOR ---
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    public void checkoutProduct(String name, String country, String city, String ccNumber, String month, String year) throws InterruptedException {
        // 1. Klik tombol Place Order
        wait.until(ExpectedConditions.visibilityOf(placeOrderButton));
        placeOrderButton.click();

        // 2. Isi Formulir
        wait.until(ExpectedConditions.visibilityOf(nameField));
        nameField.sendKeys(name);
        countryField.sendKeys(country);
        cityField.sendKeys(city);
        cardField.sendKeys(ccNumber);
        monthField.sendKeys(month);
        yearField.sendKeys(year);

        // 3. Klik Purchase
        purchaseButton.click();
    }

    public boolean isOrderSuccess() {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.isDisplayed();
    }
}