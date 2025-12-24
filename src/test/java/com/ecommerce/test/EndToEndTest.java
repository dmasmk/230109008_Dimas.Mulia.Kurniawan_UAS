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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");
    }

    @Test(priority = 1)
    public void testFullFlow() throws InterruptedException {
        // 1. Login
        loginPage = new LoginPage(driver);
        // GANTI DENGAN AKUN YANG SUDAH KAMU DAFTARKAN
        loginPage.loginToApp("dmasmk", "12345678");
        Assert.assertTrue(loginPage.isWelcomeTextDisplayed(), "Login Gagal!");

        // 2. Beli Barang
        productPage = new ProductPage(driver);
        productPage.buyProduct();
        productPage.goToCart();

        // 3. Checkout
        cartPage = new CartPage(driver);
        cartPage.checkoutProduct("Mahasiswa UAS", "123456789");

        // 4. Validasi Sukses
        Assert.assertTrue(cartPage.isOrderSuccess(), "Checkout Gagal!");
        System.out.println("TEST PASSED: User berhasil Login -> Beli -> Checkout (Firefox)");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}