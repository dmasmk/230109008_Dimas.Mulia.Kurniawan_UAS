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

    // --- LOCATORS ---
    @FindBy(linkText = "Laptops") WebElement laptopCategoryLink;
    @FindBy(linkText = "MacBook Pro") WebElement productLink;

    @FindBy(xpath = "//a[text()='Add to cart']") WebElement addToCartButton;
    @FindBy(id = "cartur") WebElement cartMenuLink;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void buyProduct() throws InterruptedException {
        // STEP 1: Klik Kategori Laptop dulu!
        wait.until(ExpectedConditions.visibilityOf(laptopCategoryLink));
        laptopCategoryLink.click();

        // STEP 2: Tunggu sebentar biar produk laptop muncul
        Thread.sleep(3000);

        // STEP 3: Klik MacBook Pro
        wait.until(ExpectedConditions.visibilityOf(productLink));
        productLink.click();

        // STEP 4: Klik Add to Cart
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();

        // STEP 5: Handle Pop-up "Product added"
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void goToCart() {
        cartMenuLink.click();
    }
}