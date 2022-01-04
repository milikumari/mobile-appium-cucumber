package com.acceptance.test;

import com.acceptance.test.utils.AppiumHelper;
import com.acceptance.test.utils.DriverManager;
import com.acceptance.test.pages.BasePage;
import com.acceptance.test.utils.LoadProperties;
import io.cucumber.java.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.Assert.assertTrue;


public class AppiumTest {

    private static String osType = LoadProperties.getProperty("platform-os-type").toLowerCase();
    private static  String testEnv = System.getProperty("test-env");
    private static  String runOnGrid = System.getProperty("run-on-grid");

    public AppiumTest(){

    }

    @Before
    public static void setUp() {

        launchMobileEnvironment();
    }

    public static void tearDown() {

        BasePage.driver.quit();
        if(!runOnGrid.equals("false")){
            AppiumHelper.stopAppiumServer();
            //AppiumHelper.stop();
        }
    }

    public static  void closeApp(){
        BasePage.driver.closeApp();
    }
    public  static  void  launchApp(){
        BasePage.driver.launchApp();
    }

    private static void launchMobileEnvironment() {
        switch(osType.toLowerCase()){
            case "android" :
                DesiredCapabilities androidCapabilities = DriverManager.setAndroidCapabilities();

                BasePage.driver = AppiumHelper.launchAppiumDriver(androidCapabilities);
                break;
            case "ios" :
                DesiredCapabilities iosCapabilities;
                iosCapabilities = DriverManager.setAndroidCapabilities();
                BasePage.driver = AppiumHelper.launchAppiumDriver(iosCapabilities);
                break;
                default:
                    assertTrue("please check osType value only android and ios are currently supported options please update details", false);
        }
    }

}
