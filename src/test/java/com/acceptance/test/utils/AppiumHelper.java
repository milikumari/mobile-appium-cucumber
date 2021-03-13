package com.acceptance.test.utils;

import com.acceptance.test.pages.BasePage;
import com.acceptance.test.utils.LoadProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class AppiumHelper {

    private  static String runOnGrid =System.getProperty("run-on-grid");


    public static AppiumDriver launchAppiumDriver(DesiredCapabilities cap) {

        String ipAddress;
        String port;

        if(runOnGrid.equals("true")){

            ipAddress= LoadProperties.getProperty("grid.url");
            port = LoadProperties.getProperty("grid.port");
        }else{
            ipAddress= LoadProperties.getProperty("appium.ipAddress");
            port= LoadProperties.getProperty("appium.port");
            appiumService =startServer(ipAddress,port);
        }
        URL driverUrl = setDriverUrl(ipAddress, port);
        AppiumDriver driver = null;
        try {
            if (System.getProperty("platform-os-type").equalsIgnoreCase("Android")) {
                driver = new AndroidDriver(driverUrl, cap);
            } else {
                driver = new IOSDriver(driverUrl, cap);
            }
            BasePage.logInfo("Appium Driver capabilities are ---> " + driver.getCapabilities());

        }catch (Exception ex){
            BasePage.logInfo("failed to launch Appium Driver with these capabilities ---> " + driver.getCapabilities());

                System.err.println("reason for failing to launch Appium is---> " + ex.getMessage());
            }
        return driver;
        }

        private  static AppiumDriverLocalService appiumService;

    private static  AppiumDriverLocalService startServer(String ipAddress, String port){

        BasePage.logInfo("going to start Appium server");

        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:/Users/Niku/node_modules/appium/build/lib/main.js"))
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                 .withIPAddress(ipAddress)
                .usingPort(Integer.parseInt(port));
        AppiumDriverLocalService service =AppiumDriverLocalService.buildService(builder);
        service.start();
        return  service;
    }

    public static  void stopAppiumServer(){

        BasePage.logInfo(" going to stop Appium server now");
        if(appiumService != null){
            appiumService.stop();
        }
        BasePage.logInfo(" Appium server is stopped ");
    }

    private  static  URL  setDriverUrl(String ipAddress, String port) {

        URL  driverUrl= null;

        try{
            driverUrl = new URL("http://"+ ipAddress +  ":" + port + "/wd/hub");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  driverUrl;
    }

}
