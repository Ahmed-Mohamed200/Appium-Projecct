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
import pages.CheckOut_OverViewPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class EndToEnd {
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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        //precondition to log in before every method
        new LoginPage(driver).enterUsername(json.getTestData("username")).enterPassword(json.getTestData("password")).clickLogin_navigateToProducts();


    }

    //this is an end to end scenario
    @Test
    public void Purchase() throws IOException {

        // we logged in , added an item to the cart then we went to the cart and clicked checkout
        // we entered the first,last name and the zipCode then we checked if the price in this page is equal to the price in the product page
        String checkPrice = new ProductsPage(driver).addToCart().clickCartIcon().clickCheckoutButton().enterFirstName(json.getTestData("firstName")).enterLastName(json.getTestData("lastName")).enterZipCode(json.getTestData("zipCode")).clickContinue().getItemText();
        Assert.assertEquals(checkPrice,"$29.99", "The price is not the same");
        //then we clicked finish and stored it into string to check on  assert if the complete message is the same
        String checkCompleteMessage = new CheckOut_OverViewPage(driver).scrollDownToFinishButton().getCompleteMessage();

        //Taking a screen shoot and save it to this specific directory
        file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/EndToEnd-ScreenShoots/E2E.jpg"));

        Assert.assertEquals(checkCompleteMessage, "THANK YOU FOR YOU ORDER");


    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
