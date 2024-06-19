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

public class ShopByDepartmentTest {
    WebDriver driver;

    @BeforeMethod
   // @Parameters("browser")
    public void setup(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get(Config.BESTBUY_URL);
    }

    @Test
    public void testShopByDepartment() {
        driver.findElement(By.linkText("Shop by Department")).click();
        driver.findElement(By.linkText("Laptops")).click();
        driver.findElement(By.cssSelector(".sku-item .sku-title a")).click();
        driver.findElement(By.cssSelector(".add-to-cart-button")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".cart-link")).isDisplayed(), "Item not added to cart!");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
