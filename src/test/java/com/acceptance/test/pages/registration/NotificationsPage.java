package com.acceptance.test.pages.registration;

import com.acceptance.test.pages.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NotificationsPage extends BasePage {
@AndroidFindAll({
        @AndroidBy(xpath="//*[@text='CLEAR']"),
        @AndroidBy(id = "dismiss_text")

})
    private MobileElement clearNotifications_Link;

    public NotificationsPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clearAllNotifications() {
        AndroidDriver androidDriver = (AndroidDriver) driver;
        androidDriver.openNotifications();
        clickOn_(clearNotifications_Link);
    }

    private void readOtp() {
        AndroidDriver androidDriver = (AndroidDriver) driver;
        androidDriver.openNotifications();
        //getOTPFromNotificationText

        List<MobileElement> allNotifications = driver.findElements(By.id("android:id/title"));
        System.out.println("no of notifications " + allNotifications.size());

        for (MobileElement webElement : allNotifications) {
            System.out.println(webElement.getText());
            if (webElement.getText().contains("Dianne")) {
                System.out.println("Notification found");
                break;
            }
        }
    }
}
