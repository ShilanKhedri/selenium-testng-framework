package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cart extends BasePage {
    public Cart(WebDriver driver) {
        super(driver);
    }
    By removeBtn = By.id("remove-sauce-labs-backpack");
    By cart = By.xpath("//div[text()='Sauce Labs Backpack']");
    public void cliclRemove(){
        driver.findElement(removeBtn).click();
    }
    public Boolean getCart(){
        return driver.findElement(cart).isDisplayed();
    }
}
