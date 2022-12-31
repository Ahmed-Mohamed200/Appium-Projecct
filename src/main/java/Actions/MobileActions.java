package Actions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;

import java.time.Duration;


public class MobileActions {
    private static WebDriver driver;

    public MobileActions(WebDriver driver) {
        MobileActions.driver = driver;
    }

    /**
     * This method is to scroll down to specific text/Element by passing only the text appeared on the screen
     *
     * @param text the text or element you want to scroll to
     */
    public static void scrollDownToSpecificText(String text) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)"
                + ".instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + text + "\").instance(0))"));
    }

    /**
     * This method is to scroll down to a text or Element that containing a text that happened by passing only the text appeared on the screen
     *
     * @param text the text or element you want to scroll to
     */
    public static void scrollDownToSpecificTextContained(String text) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)"
                + ".instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))"));
    }

    /**
     * This a private method the purpose of that is to swipe right
     *
     * @param element the webElement that you want to get its location
     * @param driver  an instance of your driver
     */
    @SuppressWarnings({"rawtypes", "unused"})
    private static void swipeRightOnElement(WebElement element, WebDriver driver) {
        Point point = element.getLocation();
        Dimension eleSize = element.getSize();
        int centerX = driver.manage().window().getSize().width / 2;
        int centerY = point.getY() + (eleSize.getHeight() / 2);
        int moveToX = driver.manage().window().getSize().width / 2;
        int moveToY = point.getY() + (eleSize.getHeight() / 2);

        System.out.println("centerX :" + centerX);
        System.out.println("moveToX :" + centerX * 8 / 5);
        System.out.println("moveToY :" + moveToY);

        new TouchAction((PerformsTouchActions) driver).press(PointOption.point(centerX, centerY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(centerX * 8 / 5, moveToY))
                .release().perform();
    }

    /**
     * This method is to swipe right
     *
     * @param element the by Element that you want to get its location and swipe right out of it
     * @param driver  an instance of your driver
     */
    public static void swipeRightOnElement(By element, WebDriver driver) {
        swipeRightOnElement(driver.findElement(element), driver);
    }

    /**
     * This a private method the purpose of that is to swipe left
     *
     * @param element the webElement that you want to get its location
     * @param driver  an instance of your driver
     */
    @SuppressWarnings({"rawtypes", "unused"})
    private static void swipeLeftOnElement(WebElement element, WebDriver driver) {
        Point point = element.getLocation();
        Dimension eleSize = element.getSize();
        int centerX = driver.manage().window().getSize().width / 2;
        int centerY = point.getY() + (eleSize.getHeight() / 2);
        int moveToX = driver.manage().window().getSize().width / 2;
        int moveToY = point.getY() + (eleSize.getHeight() / 2);

        System.out.println("centerX :" + centerX);
        System.out.println("moveToX :" + centerX * 1 / 5);
        System.out.println("moveToY :" + moveToY);

        new TouchAction((PerformsTouchActions) driver).press(PointOption.point(centerX, centerY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(centerX * 1 / 5, moveToY))
                .release().perform();
    }

    /**
     * This method is to swipe left
     *
     * @param element the by Element that you want to get its location and swipe left out of it
     */
    public static void swipeLeftOnElement(By element) {
        swipeLeftOnElement(driver.findElement(element), driver);
    }
}
