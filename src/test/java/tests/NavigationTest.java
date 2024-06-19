package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.Config;

import java.util.List;

public class NavigationTest {
    WebDriver driver;

    // Setup method to initialize WebDriver
    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get(Config.BESTBUY_URL);
    }

    // Test method for navigation functionality
    @Test
    public void testNavigation() {
        List<WebElement> menuItems = driver.findElements(By.cssSelector("nav[aria-label='Main'] a"));
        for (WebElement item : menuItems) {
            item.click();
            Assert.assertFalse(driver.getTitle().isEmpty(), "Navigation failed!");
            driver.navigate().back();
        }
    }

    // Teardown method to close the WebDriver
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
