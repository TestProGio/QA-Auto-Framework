/*
//Chrome Version of WebDriver***********************
package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverManagerUtil {

    private WebDriver driver;
    private WebDriverWait wait;

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    // Method to set up WebDriver with ChromeOptions

    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Thread.sleep(1000); // Adding a short delay
    }


    // Method to tear down the WebDriver
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

 */
package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverManagerUtil {

    private static WebDriverManagerUtil instance;
    private WebDriver driver;
    private WebDriverWait wait;

    // Private constructor to prevent instantiation
    private WebDriverManagerUtil() {
        // Setup the WebDriver when the instance is created
        setup();
    }

    // Static method to get the single instance of WebDriverManagerUtil
    public static WebDriverManagerUtil getInstance() {
        if (instance == null) {
            instance = new WebDriverManagerUtil();
        }
        return instance;
    }

    // Getter for WebDriver
    public WebDriver getDriver() {
        return driver;
    }

    // Getter for WebDriverWait
    public WebDriverWait getWait() {
        return wait;
    }

    // Method to set up WebDriver with ChromeOptions
    private void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Method to tear down the WebDriver
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            instance = null; // Reset instance for future tests
        }
    }
}



