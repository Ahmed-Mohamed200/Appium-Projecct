package Tests;

import com.shaft.driver.SHAFT;
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
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Login {
    WebDriver driver;
    File file;

    //created an object from the JSONFileManager class to get the data from an exernal file
    JSONFileManager json = new JSONFileManager("src/test/resources/TestDataFiles/ExternalData.json");

    //this method will run before every method with its capabilities (Same capabilities in the appium inspector)
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


    }

    //happy login scenario
    @Test
    public void loginSuccessfully() throws IOException {
        //String to store the value of the products page header(Title)
        String check_ProductsTitle = new LoginPage(driver).enterUsername(json.getTestData("username")).enterPassword(json.getTestData("password")).clickLogin_navigateToProducts().getProductsTitle();

        //Taking a screen shoot and store it in this directory
        file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/LoginScreenShoots/Login.jpg"));

        // assert to check if both the titles are the same

        Assert.assertEquals(check_ProductsTitle, "PRODUCTS");


    }

    //Wrong password Scenario
    @Test
    public void loginFailed() throws IOException {
        //String to store the error message because we entered wrong password
        String check_ErrorMessage = new LoginPage(driver).enterUsername(json.getTestData("username")).enterPassword(json.getTestData("wrongPassword")).clickLogin().getErrorMessage();

        //Taking a screen shoot and store it in this directory
        file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/LoginScreenShoots/LoginFailed.jpg"));

        //assert to check if the error message as the same as expected
        Assert.assertEquals(check_ErrorMessage, "Username and password do not match any user in this service.");

    }

    //Quit the driver(close all the tabs) when the driver finishes executing all the test cases
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
