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

    @FindBy(xpath = "//button[text()='Place Order']") WebElement placeOrderButton;
    @FindBy(id = "name") WebElement nameField;
    @FindBy(id = "card") WebElement cardField;
    @FindBy(xpath = "//button[text()='Purchase']") WebElement purchaseButton;
    @FindBy(xpath = "//h2[text()='Thank you for your purchase!']") WebElement successMessage;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void checkoutProduct(String name, String ccNumber) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(placeOrderButton));
        placeOrderButton.click();

        wait.until(ExpectedConditions.visibilityOf(nameField));
        nameField.sendKeys(name);
        cardField.sendKeys(ccNumber);
        purchaseButton.click();
    }

    public boolean isOrderSuccess() {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.isDisplayed();
    }
}