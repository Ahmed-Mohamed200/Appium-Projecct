package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    //Locators
    By cartItem_Title = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]");
    By cartItem_Price = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView");
    By remove_Button=By.xpath("//android.view.ViewGroup[@content-desc=\"test-REMOVE\"]");
    By cart_Quantity=By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.TextView");
    By checkOut_Button=By.xpath("//android.view.ViewGroup[@content-desc=\"test-CHECKOUT\"]");

    //Methods
    public String getCartItem_Title()  {
      return driver.findElement(cartItem_Title).getText();
    }
    public String getCartItem_price() {
        return driver.findElement(cartItem_Price).getText();
    }
    public void removeFromCart(){
        driver.findElement(remove_Button).click();
    }
    public boolean checkCartIsEmpty()  {
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(cartItem_Title)));
        return driver.findElements(cartItem_Title).isEmpty();

    }
    public boolean checkCartQuantity()  {
        wait.until(ExpectedConditions.invisibilityOfElementLocated((cart_Quantity)));
     return driver.findElements(cart_Quantity).isEmpty();
    }
    public CheckOutPage clickCheckoutButton(){
        driver.findElement(checkOut_Button).click();
        return new CheckOutPage(driver);
    }




}
