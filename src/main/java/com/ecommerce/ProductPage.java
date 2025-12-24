package com.ecommerce;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {
    WebDriver driver;
    WebDriverWait wait;

    // Pilih HP Samsung Galaxy S6
    @FindBy(linkText = "Samsung galaxy s6") WebElement productLink;
    @FindBy(xpath = "//a[text()='Add to cart']") WebElement addToCartButton;
    @FindBy(id = "cartur") WebElement cartMenuLink;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void buyProduct() throws InterruptedException {
        Thread.sleep(2000); // Tunggu home loading
        productLink.click();
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();

        // Handle Pop-up "Product added"
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept(); // Klik OK di pop-up
    }

    public void goToCart() {
        cartMenuLink.click();
    }
}
