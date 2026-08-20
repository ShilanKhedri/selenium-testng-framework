package com.Shilan.tests;

import com.Shilan.utils.ConfigReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected Cart cartPage;
//    protected data data;
    public WebDriver getDriver() {
        return driver;
    }
    @BeforeMethod
    public void setUp(){
        String browser = ConfigReader.getProperty("browser");

        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                driver = new ChromeDriver();
                break;
        }


        int timeout = Integer.parseInt(ConfigReader.getProperty("timeout"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

        driver.get(ConfigReader.getProperty("baseUrl"));
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new Cart(driver);
//        data = new data();

    }
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {

        driver.quit();
    }
}
