package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {
    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;

    }
    //Locators
    By first_name=new AppiumBy.ByAccessibilityId("test-First Name");
    By last_name=new AppiumBy.ByAccessibilityId("test-Last Name");
    By zip_code=new AppiumBy.ByAccessibilityId("test-Zip/Postal Code");
    By continue_Button=new AppiumBy.ByAccessibilityId("test-CONTINUE");

    //Methods
    public CheckOutPage enterFirstName (String firstname)
    {
        driver.findElement(first_name).sendKeys(firstname);
        return this;
    }
    public CheckOutPage enterLastName (String lastname)
    {
        driver.findElement(last_name).sendKeys(lastname);
        return this;
    }
    public CheckOutPage enterZipCode (String zipCode)
    {
        driver.findElement(zip_code).sendKeys(zipCode);
        return this;
    }
    public CheckOut_OverViewPage clickContinue ()
    {
        driver.findElement(continue_Button).click();
        return new CheckOut_OverViewPage(driver);
    }
}
