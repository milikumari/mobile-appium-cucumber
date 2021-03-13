package com.acceptance.test.pages;

import com.acceptance.test.utils.LoadProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.cucumber.java.Scenario;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;


public class BasePage {
    private static String osType = System.getProperty("platform-os-type");
    public static boolean iOS = osType.equalsIgnoreCase("iOS") ? true : false;
    public static boolean Android = osType.equalsIgnoreCase("Android") ? true : false;

    public static AppiumDriver driver;

    protected final static Logger logger = LogManager.getLogger(BasePage.class);

    public static void logInfo(String message) {
        logger.info(message);
    }
    private static void printPageSource() {
        System.err.println(driver.getPageSource());
    }
    protected static String getPageSource() {
        return  driver.getPageSource();
    }

    public static void takeScreenShotOnFailure(Scenario scenario) {
        try{
            final byte[]  screenshot = driver.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "img/png", scenario.getName());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void stopVideoRecording(Scenario scenario) throws IOException {

        String scenarioName= scenario.getName().replaceAll("\\s", "_").replaceAll(":", "");
        String videoRecordingFile = ((CanRecordScreen)driver).stopRecordingScreen();
        byte[]  recordedVideo = Base64.decodeBase64(videoRecordingFile);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh_mm");
        String testEnv= System.getProperty("test.env")+"/";
        String testPlatform =System.getProperty("platform-os-type").toLowerCase()+ "/";
        String testStatus= "passed/";
        if(scenario.isFailed()){
            testStatus ="failed/";
        }
        String destinationPath= System.getProperty("user.dir")+
                "/src/test/resources/testOutput/videos/" + testPlatform + testStatus + testEnv + scenarioName
                + "_" + sdf.format(new Date())+ ".mp4";
        Path path = Paths.get(destinationPath);
        Files.write(path, recordedVideo);
    }

    public static void startVideoRecording() {

        logInfo("Appium is going to start  video recording");

        if(Android){
            ((CanRecordScreen)driver).startRecordingScreen(new AndroidStartScreenRecordingOptions()
                    .withTimeLimit(Duration.ofSeconds(1800)));
        }else{
            ((CanRecordScreen)driver).startRecordingScreen(new IOSStartScreenRecordingOptions()
                    .withTimeLimit(Duration.ofSeconds(1800)).withVideoScale("1280x720"));
        }
    }
}

