package com.acceptance.test.utils;

import io.appium.java_client.remote.MobileCapabilityType;
import org.assertj.core.api.Fail;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Properties;

import static org.junit.Assert.assertTrue;

public class DriverManager {

    private static final DesiredCapabilities capabilities = new DesiredCapabilities();
    private static final String deviceType = System.getProperty("device-type");
    private static final Properties props = LoadProperties.getProps();

    private static String appPackageName;
    private static String appActivityName;
    private static String appPath;



    public static DesiredCapabilities setAndroidCapabilities() {
        setAndroidAppDetails();
        System.err.println("set common Android capabilities");

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", System.getProperty("user.dir")+ props.getProperty("android-app"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, System.getProperty("android-device-name"));
        // capabilities.setCapability("locationServiceEnabled",false);
       // capabilities.setCapability("noReset", true);
        //fullrestwill uninstalled app after test is completed
         capabilities.setCapability("fullReset",true);
        capabilities.setCapability("appPackage", "com.whatsapp");
        capabilities.setCapability("appActivity", "com.whatsapp.Main");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("platformVersion", System.getProperty("android-platform-version"));

        switch (deviceType) {
            case "emulator":
                capabilities.setCapability("platformName: ", "Android");
                capabilities.setCapability("newCommanTimeout", 6000);
                capabilities.setCapability("autoGrantPermissions", true);
                capabilities.setCapability("autoAcceptAlerts", false);
                break;
            case "device":
                capabilities.setCapability("newCommanTimeout", 6000);
                capabilities.setCapability(MobileCapabilityType.UDID, System.getProperty("android-udid"));
                capabilities.setCapability("autoAcceptAlerts", false);
                break;
            default:
                assertTrue("please provide valid option", false);
        }
        return capabilities;
    }

    public static DesiredCapabilities setIosCapabilities() {

        System.err.println("set common IOS capabilities");

        capabilities.setCapability("connectHardwareKeyboard", true);
        capabilities.setCapability("showIOSLog", true);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, System.getProperty("ios-device-name"));
        capabilities.setCapability("locationServiceEnabled", false);
        //capabilities.setCapability("noReset",true);

        switch (deviceType) {
            case "emulator":
                capabilities.setCapability("connectHardwareKeyboard", true);
                capabilities.setCapability("newCommanTimeout", 6000);
                capabilities.setCapability("autoGrantPermissions", true);
                capabilities.setCapability("autoAcceptAlerts", false);
                break;
            case "device":
                capabilities.setCapability(MobileCapabilityType.UDID, System.getProperty("udid"));
                capabilities.setCapability("autoAcceptAlerts", false);
                break;

            default:

                assertTrue("please provide valid option", false);
        }
        return capabilities;
    }

    public static void setAndroidAppDetails() {
        switch (System.getProperty("android-app-name").toLowerCase()) {

            case "calculator":
                appPackageName = "com.oneplus.calculator";
                appActivityName = "com.oneplus.calculator.Calculator";
                break;
            case "contacts":
                appPackageName = "com.jayway.contacts";
                appActivityName = "com.jayway.contacts.MainActivity";
                break;
            case "whatsapp":
                appPackageName = "com.whatsapp";
                appActivityName = "com.whatsapp.Main";
                appPath=System.getProperty("user.dir")+ props.getProperty("android-app");
                break;
            default:
                Fail.fail("please set new app package and app activity details or provide .apk");
        }

    }
}
