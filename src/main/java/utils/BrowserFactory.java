package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {
	
	// Method to initialize WebDriver based on the browser type
    public static WebDriver getDriver(String browser) {
        WebDriver driver;

        // Initialize Chrome driver
        if (browser.equalsIgnoreCase("chrome")) {
        	//driver= new ChromeDriver();
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Run in headless mode
            driver = new ChromeDriver(options);

        // Initialize Firefox driver
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless"); // Run in headless mode
            driver = new FirefoxDriver(options);

         // Initialize Edge driver
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            // Edge does not support headless mode as of the latest versions
            driver = new EdgeDriver(options);
            
        // Throw an error for unsupported browser
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        return driver;
    }

}