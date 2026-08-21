package com.shilan.tests;
import com.shilan.listeners.TestListener;
import com.shilan.utils.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

@Listeners(TestListener.class)
public class FirstAutomationTest extends BaseTest{
    private static final Logger log = LoggerFactory.getLogger(FirstAutomationTest.class);

    @DataProvider(name = "loginExcelData")
    public Object[][] getLoginData() {
        String filePath = "src/test/resources/test-data/LoginData.xlsx";
        String sheetName = "Login";

        Object[][] data = ExcelReader.getSheetData(filePath, sheetName);

        for (int i = 0; i < data.length; i++) {
            data[i][3] = Boolean.parseBoolean(data[i][3].toString());
        }

        return data;
    }
    @Test(dataProvider = "loginExcelData")
    public void validLoginTest(String testCase, String username, String password, boolean expectedSuccess) {

        System.out.println("Running: " + testCase);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        if (expectedSuccess) {
            WebElement title = loginPage.getWait().until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("title"))
            );
            Assert.assertEquals(inventoryPage.getTitle(), "Products");
        } else {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement error = wait.until(
                    ExpectedConditions.elementToBeClickable(By.className("error-button"))
            );
            Assert.assertTrue(error.isDisplayed());
        }
    }


    @Test
    public void addProduct() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        inventoryPage.clickAddToCart();
        inventoryPage.clickCart();
        Assert.assertTrue(cartPage.getCart());
    }

    @Test
    public void removeFromCart(){
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        inventoryPage.clickAddToCart();
        inventoryPage.clickCart();
        cartPage.cliclRemove();
        List<WebElement> products =
                driver.findElements(By.id("remove-sauce-labs-backpack"));
        Assert.assertTrue(products.isEmpty());
    }

}
