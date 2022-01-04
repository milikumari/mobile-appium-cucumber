package com.acceptance.test.utils;

import com.acceptance.test.pages.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URL;
import java.util.HashMap;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppiumHelper {

    private static final String runOnGrid = System.getProperty("run-on-grid");
    private static AppiumDriverLocalService appiumService;
    static AppiumDriverLocalService server;


    public static AppiumDriver launchAppiumDriver(DesiredCapabilities cap) {

        String ipAddress;
        String port;
        if (runOnGrid.equals("true")) {
            ipAddress = LoadProperties.getProperty("grid.url");
            port = LoadProperties.getProperty("grid.port");
        } else {
            ipAddress = LoadProperties.getProperty("appium.ipAddress");
            port = LoadProperties.getProperty("appium.port");
            appiumService = startServer(ipAddress, port);
            //start();

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
        } catch (Exception ex) {
            BasePage.logInfo("failed to launch Appium Driver with these capabilities ---> " + driver.getCapabilities());
            System.err.println("reason for failing to launch Appium is---> " + ex.getMessage());
        }
        return driver;
    }

    private static AppiumDriverLocalService startServer(String ipAddress, String port) {

        BasePage.logInfo("going to start Appium server");
        String path = LoadProperties.getProperty("appium.js.path");

        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File(path))
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                .withIPAddress(ipAddress)
                .usingPort(Integer.parseInt(port));
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        service.start();
        return service;
    }

    public static void stopAppiumServer() {

        BasePage.logInfo(" going to stop Appium server now");
        if (nonNull(appiumService) && appiumService.isRunning()) {
            appiumService.stop();
        }
        BasePage.logInfo(" Appium server is stopped ");
    }

    private static URL setDriverUrl(String ipAddress, String port) {

        URL driverUrl = null;
        try {
            driverUrl = new URL("http://" + ipAddress + ":" + port + "/wd/hub");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return driverUrl;
    }
//
//    public static void start(){
//        if(isPortAvailable(Integer.parseInt(LoadProperties.getProperty("appium.port")))){
//            getInstance().start();
//            System.out.println("Server started from here!");
//        }else
//            System.out.println("Server already running!");
//    }

//    static AppiumDriverLocalService getInstance() {
//        if (server == null) {
//            setInstance();
//            server.clearOutPutStreams(); //stop printing appium logs to console
//        }
//        return server;
//    }

//    static void setInstance() {
//        HashMap<String, String> environment = new HashMap();
//        //path to carthage
//        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
//
//        AppiumServiceBuilder builder = new AppiumServiceBuilder();
//        builder
//                .withAppiumJS(new File(LoadProperties.getProperty("appium.js.path")))
//                .withIPAddress(LoadProperties.getProperty("appium.ipAddress"))
//                .usingPort(Integer.parseInt(LoadProperties.getProperty("appium.port")))
//                .withEnvironment(environment)
//                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
//                //.withArgument(GeneralServerFlag.LOG_LEVEL, "WARN")
//                .withLogFile(new File("AppiumLog.txt"));
//
//        server = AppiumDriverLocalService.buildService(builder);
//    }

//    public static boolean isPortAvailable(int port) {
//        //applicable for tcp ports
//        try (ServerSocket serverSocket = new ServerSocket()) {
//            // setReuseAddress(false) is required only on OSX,
//            // otherwise the code will not work correctly on that platform
//            serverSocket.setReuseAddress(false);
//            serverSocket.bind(new InetSocketAddress(InetAddress.getByName("localhost"), port), 1);
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
//    }
//
//    public static void stop() {
//        try {
//            if (server != null) {
//                getInstance().stop();
//               // Thread.sleep(2000);
//                System.out.println("Appium server stopped!");
//                assertFalse((server.isRunning()));
//            }
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//    }
}
