package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.Config;


public class LoginTest {
    WebDriver driver;
    WebDriverWait wait;


    // Setup method to initialize WebDriver
    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        driver = BrowserFactory.getDriver(browser);
        wait = new WebDriverWait(driver, Duration.ofSeconds(240)); // Initialize WebDriverWait with a timeout of 10 seconds
        driver.get(Config.BESTBUY_URL);
    }

    // Test method for login functionality
    @Test
    public void testLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[lang='en'] img[alt='United States']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Account"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign In"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fld-e"))).sendKeys(Config.USERNAME);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fld-p1"))).sendKeys(Config.PASSWORD);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cia-form__controls__submit"))).click();
        Assert.assertTrue(driver.getPageSource().contains("Welcome"), "Login failed!");

    /*	driver.findElement(By.cssSelector("div[lang='en'] img[alt='United States']")).click();
        driver.findElement(By.linkText("Account")).click();
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("fld-e")).sendKeys(Config.USERNAME);
        driver.findElement(By.id("fld-p1")).sendKeys(Config.PASSWORD);
        driver.findElement(By.cssSelector(".cia-form__controls__submit")).click();
       */ Assert.assertTrue(driver.getPageSource().contains("Welcome"), "Login failed!");
    }

    // Teardown method to close the WebDriver
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
