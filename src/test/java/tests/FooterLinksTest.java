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

public class FooterLinksTest {
    WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        driver = BrowserFactory.getDriver(browser);
        driver.get(Config.BESTBUY_URL);
    }

    @Test
    public void testFooterLinks() {
        List<WebElement> footerLinks = driver.findElements(By.cssSelector("footer a"));
        for (WebElement link : footerLinks) {
            String href = link.getAttribute("href");
            driver.navigate().to(href);
            Assert.assertFalse(driver.getTitle().isEmpty(), "Footer link navigation failed!");
            driver.navigate().back();
        }
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
