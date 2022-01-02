package com.acceptance.test.stepdefinitions;

import com.acceptance.test.pages.BasePage;
import com.acceptance.test.utils.ScenarioProvider;
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


public class BaseSteps {
    protected  final ScenarioProvider scenarioProvider;
    //Lo4j has reported some vulnerability  I will remove lo4j  soon
    private final Logger logger;
    public  BaseSteps(ScenarioProvider scenarioProvider ){
        this.scenarioProvider= scenarioProvider;
        this.logger= LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }
}