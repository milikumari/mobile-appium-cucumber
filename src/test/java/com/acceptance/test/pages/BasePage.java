package com.acceptance.test.pages;

import com.acceptance.test.utils.LoadProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.Scenario;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;


public class BasePage {
    protected final static Logger logger = LogManager.getLogger(BasePage.class);
    private static final int TIMEOUT = Integer.parseInt(LoadProperties.getProperty("appium-wait-timeout"));
    public static AppiumDriver driver;
    private static final String osType = System.getProperty("platform-os-type");
    public static boolean iOS = osType.equalsIgnoreCase("iOS");
    public static boolean Android = osType.equalsIgnoreCase("Android");
    public static boolean isRunningOnAndroidEmulator = System.getProperty("device-type").equalsIgnoreCase("emulator");


    public static void logInfo(String message) {
        logger.info(message);
    }

    private static void printPageSource() {
        System.err.println(driver.getPageSource());
    }

    protected static String getPageSource() {
        return driver.getPageSource();
    }

    public static void takeScreenShotOnFailure(Scenario scenario) {
        try {
            final byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "img/png", scenario.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void enterValueInToField(MobileElement element, String textValue) {
        element.sendKeys(textValue);
    }

    public static void clickOn_(MobileElement element) {
        try{ element.click();}
        catch (Exception e)
        {
            waitForElementToVisible(element);
            clickOn_(element);
        }
    }

    public static boolean isElementVisible(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static void waitForElementToVisible(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void longPress() throws InterruptedException {
        MobileElement longPress = (MobileElement) new WebDriverWait(driver,5).
                until(elementToBeClickable(MobileBy.AccessibilityId("longpress")));
        new Actions(driver).clickAndHold(longPress).perform();
    }

    public static void stopVideoRecording(Scenario scenario) throws IOException {

        String scenarioName = scenario.getName().replaceAll("\\s", "_").replaceAll(":", "");
        String videoRecordingFile = ((CanRecordScreen) driver).stopRecordingScreen();
        byte[] recordedVideo = Base64.decodeBase64(videoRecordingFile);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh_mm");
        String testEnv = System.getProperty("test.env") + "/";
        String testPlatform = System.getProperty("platform-os-type").toLowerCase() + "/";
        String testStatus = "passed/";
        if (scenario.isFailed()) {
            testStatus = "failed/";
        }
        String destinationPath = System.getProperty("user.dir") +
                "/src/test/resources/testOutput/videos/" + testPlatform + testStatus + testEnv + scenarioName
                + "_" + sdf.format(new Date()) + ".mp4";
        Path path = Paths.get(destinationPath);
        Files.write(path, recordedVideo);
    }

    public static void startVideoRecording() {

        logInfo("Appium is going to start  video recording");

        if (Android) {
            ((CanRecordScreen) driver).startRecordingScreen(new AndroidStartScreenRecordingOptions()
                    .withTimeLimit(Duration.ofSeconds(1800)));
        } else {
            ((CanRecordScreen) driver).startRecordingScreen(new IOSStartScreenRecordingOptions()
                    .withTimeLimit(Duration.ofSeconds(1800)).withVideoScale("1280x720"));
        }
    }
    //using TouchAction

    public static void tabOnLinkElementByTextCoordinatePoints(MobileElement element, String text) {

        Point point = element.getLocation();
        int x = point.x + element.getSize().getWidth() - 2;
        int y = point.y + element.getSize().getHeight() - 1;
        new TouchAction<>(driver).tap(PointOption.point(x, y)).perform();
    }

    // using javaScript Driver
    public static void scrollInTheDirection(String direction) {

        JavascriptExecutor js = driver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", direction.toLowerCase());
        js.executeScript("mobile : scroll", scrollObject);
    }

    public static void tabOnTheLinkElement(MobileElement element) {

        JavascriptExecutor js = driver;
        js.executeScript("arguments[0].click;", element);
    }

    public static void scrollToElement(MobileElement element, String direction) {

        JavascriptExecutor js = driver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", direction.toLowerCase());
        scrollObject.put("element", element.getId());
        js.executeScript("mobile : scroll", scrollObject);
    }


    // js.executeScript("mobile: getNotifications");

    //Map<String, Object> notifications = (Map<String, Object>)driver.executeScript("mobile: getNotifications");
    private void manageNotifications(Boolean show) {

        Dimension screenSize;
        screenSize = driver.manage().window().getSize();
        int yMargin = 5;
        int xMid = screenSize.width / 2;
        PointOption top = PointOption.point(xMid, yMargin);
        PointOption bottom = PointOption.point(xMid, screenSize.height - yMargin);
        TouchAction action = new TouchAction(driver);
        if (show) {
            action.press(top);
        } else {
            action.press(bottom);
        }
        action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
        if (show) {
            action.moveTo(bottom);
        } else {
            action.moveTo(top);
        }
        action.perform();
    }

    private void showNotifications() {
        manageNotifications(true);
    }

    private void hideNotifications() {
        manageNotifications(false);
    }
}


