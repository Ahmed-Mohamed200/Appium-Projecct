package Tests;

import com.shaft.tools.io.JSONFileManager;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AddToCart {
    WebDriver driver;
    File file;
    JSONFileManager json=new JSONFileManager("src/test/resources/TestDataFiles/ExternalData.json");

    @BeforeMethod
    public void setupDevice() throws MalformedURLException {
        String AppName = System.getProperty("user.dir") + "\\src\\test\\resources\\TestDataFiles\\Android.SauceLabs.Mobile.Sample.app.2.2.0.apk";
        System.out.println(System.getProperty("user.dir"));
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "Bando");
        caps.setCapability("appium:app", AppName);
        caps.setCapability("appium:platformVersion", "11");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:appWaitActivity", "com.swaglabsmobileapp.MainActivity");
        driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //precondition to log in before every method
        new LoginPage(driver).enterUsername(json.getTestData("username")).enterPassword(json.getTestData("password")).clickLogin_navigateToProducts();


    }

    @Test
    public void AddItemToCart() throws IOException {
        //get both the title and the price for the item in the product page
        String getProductTitle =new ProductsPage(driver).getFirstProduct_Title();
        String getProductPrice = new ProductsPage(driver).getFirstProduct_price();
        //Click the cart icon to open the cart page
        new ProductsPage(driver).addToCart().clickCartIcon();
        //get both the title and the price for the item in the cart page
        String getCartTitle = new CartPage(driver).getCartItem_Title();
        String getCartPrice = new CartPage(driver).getCartItem_price();

        file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/Cart-ScreenShoots/CartItem.jpg"));

        //assert that both the price and the title are the same (product page = cart page)
        Assert.assertEquals(getProductTitle,getCartTitle,"both of the titles are not equal");
        Assert.assertEquals(getProductPrice,getCartPrice,"both of the prices are not equal");


    }
    @Test
    public void removeFromCart() {
        //add an item then remove it
       new ProductsPage(driver).addToCart().clickCartIcon().removeFromCart();

        //boolean to return true that the cart is empty then we asserted it
        Boolean cartTitle = new CartPage(driver).checkCartIsEmpty();
        Assert.assertTrue(cartTitle,"your cart is still displayed");
        //boolean to return true that the quantity =0 (there is no number on the cart icon)
        Boolean cartQuantity=new CartPage(driver).checkCartQuantity();
        Assert.assertTrue(cartQuantity,"there is items in your cart check again");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
