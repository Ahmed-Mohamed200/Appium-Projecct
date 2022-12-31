package pages;

import Actions.MobileActions;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOut_OverViewPage {
    WebDriver driver;
    MobileActions mobileActions;

    public CheckOut_OverViewPage(WebDriver driver) {
        this.driver = driver;

    }

    By finish_Button = new AppiumBy.ByAccessibilityId("test-FINISH");
    By item_Price=By.xpath("//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView");

    public String getItemText(){
        return driver.findElement(item_Price).getText();
    }

    public CheckOut_Complete scrollDownToFinishButton() {
        new MobileActions(driver);
        mobileActions.scrollDownToSpecificText("FINISH");
        driver.findElement(finish_Button).click();
        return new CheckOut_Complete(driver);
    }
}