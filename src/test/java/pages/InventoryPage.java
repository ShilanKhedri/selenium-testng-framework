package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage extends BasePage{
    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    public By title = By.className("title");
    By addToCart = By.name("add-to-cart-sauce-labs-backpack");
    By cart = By.className("shopping_cart_link");
    public void clickAddToCart(){
        driver.findElement(addToCart).click();
    }
    public void clickCart(){
        driver.findElement(cart).click();
    }
    public WebElement title(){
        return driver.findElement(title);
    }
    public String getTitle(){
        return driver.findElement(title).getText();
    }
}
