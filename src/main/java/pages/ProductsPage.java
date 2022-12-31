package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
    WebDriver driver;
    //Locators
    By products_Title = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Toggle\"]//parent::android.view.ViewGroup/android.widget.TextView");
    By addToCart_Button = By.xpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]");
    By cart_Icon = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView");
    By firstProduct_Title = By.xpath("(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]");
    By firstProduct_price = By.xpath("(//android.widget.TextView[@content-desc=\"test-Price\"])[1]");

    //Methods
    public ProductsPage(WebDriver driver) {
        this.driver = driver;

    }

    public String getProductsTitle() {

        return driver.findElement(products_Title).getText();
    }

    public ProductsPage addToCart() {
    driver.findElement(addToCart_Button).click();
    return this;

    }
    public CartPage clickCartIcon(){
        driver.findElement(cart_Icon).click();
        return new CartPage(driver);
    }

    public String getFirstProduct_Title() {
              return   driver.findElement(firstProduct_Title).getText();
    }

    public String getFirstProduct_price() {
        return driver.findElement(firstProduct_price).getText();
    }

}
