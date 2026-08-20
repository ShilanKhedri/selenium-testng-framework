package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
     By username = By.id("user-name");
     By password = By.id("password");
     By loginBtn = By.id("login-button");
     public LoginPage(WebDriver driver) {
          super(driver);
     }
     public void enterUsername(String username) {
          driver.findElement(this.username)
                  .sendKeys(username);
     }
     public void enterPassword(String password) {
          driver.findElement(this.password)
                  .sendKeys(password);
     }
     public void clickLogin() {
          driver.findElement(loginBtn)
                  .click();
     }
     public WebDriverWait getWait(){
          return wait;
     }
}
