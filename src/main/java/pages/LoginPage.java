package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;

    }

    //Locators
    By userName_Field = new AppiumBy.ByAccessibilityId("test-Username");
    By password_Field = new AppiumBy.ByAccessibilityId("test-Password");
    By login_Button = new AppiumBy.ByAccessibilityId("test-LOGIN");
    By error_message=By.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");

    //Methods
    public LoginPage enterUsername (String username)
    {
        driver.findElement(userName_Field).sendKeys(username);
        return this;
    }
    public LoginPage enterPassword (String password)
    {
        driver.findElement(password_Field).sendKeys(password);
        return this;
    }
    public ProductsPage clickLogin_navigateToProducts ()
    {
        driver.findElement(login_Button).click();
        return new ProductsPage(driver);
    }

    //this method for the login-failed test case because the previous one navigates us to a new page.
    public LoginPage clickLogin(){
         driver.findElement(login_Button).click();
         return this;
    }

    public String getErrorMessage() {
        return driver.findElement(error_message).getText();

    }








}
