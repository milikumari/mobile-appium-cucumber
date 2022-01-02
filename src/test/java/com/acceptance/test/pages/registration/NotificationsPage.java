package com.acceptance.test.pages.registration;

import com.acceptance.test.pages.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NotificationsPage extends BasePage {

    @AndroidBy(id = "com.android.systemui:id/dismiss_text")
    MobileElement clearNotifications_Link;

    public NotificationsPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clearAllNotifications() {
        AndroidDriver androidDriver = (AndroidDriver) driver;
        androidDriver.openNotifications();
        clickOn_(clearNotifications_Link);
    }

    public void readOtp() {
        AndroidDriver androidDriver = (AndroidDriver) driver;
        androidDriver.openNotifications();
        //getOTPFromNotificationText

        List<MobileElement> allnotifications = driver.findElements(By.id("android:id/title"));
        System.out.println("no of notifications " + allnotifications.size());

        for (MobileElement webElement : allnotifications) {
            System.out.println(webElement.getText());
            if (webElement.getText().contains("Dianne")) {
                System.out.println("Notification found");
                break;
            }
        }
    }
}
