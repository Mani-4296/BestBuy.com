package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.Config;

public class CheckoutTest {
    WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get(Config.BESTBUY_URL);
    }

    @Test
    public void testCheckout() {
        // Login
        driver.findElement(By.linkText("Account")).click();
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("fld-e")).sendKeys(Config.USERNAME);
        driver.findElement(By.id("fld-p1")).sendKeys(Config.PASSWORD);
        driver.findElement(By.cssSelector(".cia-form__controls__submit")).click();

        // Add item to cart
        driver.findElement(By.id("gh-search-input")).sendKeys("laptop");
        driver.findElement(By.cssSelector(".header-search-button")).click();
        driver.findElement(By.cssSelector(".sku-item .sku-title a")).click();
        driver.findElement(By.cssSelector(".add-to-cart-button")).click();
        
        // Go to cart and proceed to checkout
        driver.findElement(By.cssSelector(".cart-link")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click(); // Click checkout button
        
        // Fill out dummy payment information
        driver.findElement(By.id("firstName")).sendKeys("Mani");
        driver.findElement(By.id("lastName")).sendKeys("Kandan");
        driver.findElement(By.id("addressLine1")).sendKeys("123 Main St");
        driver.findElement(By.id("city")).sendKeys("New York");
        driver.findElement(By.id("state")).sendKeys("NY");
        driver.findElement(By.id("postalCode")).sendKeys("10001");
        driver.findElement(By.id("creditCardNumber")).sendKeys("4111111111111111");
        driver.findElement(By.id("expirationMonth")).sendKeys("12");
        driver.findElement(By.id("expirationYear")).sendKeys("2023");
        driver.findElement(By.id("cvv")).sendKeys("123");
        driver.findElement(By.cssSelector(".btn-primary")).click();
        
        // Verify order confirmation
        Assert.assertTrue(driver.getPageSource().contains("Order Confirmation"), "Order not placed successfully!");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
