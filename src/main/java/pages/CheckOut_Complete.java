package pages;

import Actions.MobileActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOut_Complete {
    WebDriver driver;


    public CheckOut_Complete(WebDriver driver) {
        this.driver = driver;

    }
    By completeMessage= By.xpath("//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: COMPLETE!\"]/android.view.ViewGroup/android.widget.TextView[1]");

    public String getCompleteMessage(){
        return driver.findElement(completeMessage).getText();

    }
}
