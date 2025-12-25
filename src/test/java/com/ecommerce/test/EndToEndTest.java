package com.ecommerce.test;

import com.ecommerce.CartPage;
import com.ecommerce.LoginPage;
import com.ecommerce.ProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class EndToEndTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        // Implicit wait tetap dipakai untuk jaga-jaga elemen lambat loading
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");
    }

    @Test(priority = 1)
    public void testFullFlow() throws InterruptedException {
        // 1. Login
        loginPage = new LoginPage(driver);
        loginPage.loginToApp("dmasmk", "12345678");

        Thread.sleep(3000);
        Assert.assertTrue(loginPage.isWelcomeTextDisplayed(), "Login Gagal! Teks Welcome tidak muncul.");

        // 2. Beli Barang
        productPage = new ProductPage(driver);
        productPage.buyProduct();

        Thread.sleep(2000);

        productPage.goToCart();

        // 3. Checkout
        cartPage = new CartPage(driver);
        cartPage.checkoutProduct(
                "dmasmk",        // Name
                "Indonesia",     // Country
                "Cilacap",       // City
                "0000111145",    // Credit Card
                "12",            // Month
                "2025"           // Year
        );

        // 4. Validasi Sukses
        Thread.sleep(1000);
        Assert.assertTrue(cartPage.isOrderSuccess(), "Checkout Gagal! Pesan sukses tidak muncul.");
        System.out.println("TEST PASSED: User berhasil Login -> Beli -> Checkout (Firefox)");
    }

    @AfterClass
    public void tearDown() {
        // Jeda sebentar sebelum browser menutup agar bisa melihat hasilnya
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (driver != null) {
            driver.quit();
        }
    }
}