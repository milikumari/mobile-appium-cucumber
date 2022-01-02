package com.acceptance.test.stepdefinitions;

import com.acceptance.test.AppiumTest;
import com.acceptance.test.pages.BasePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;

public class SetUp {

    private static final boolean isAppAlreadyLaunched = false;


    @After
    public static void afterScenario(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {

            BasePage.takeScreenShotOnFailure(scenario);
        }

        if (BasePage.Android) {
            BasePage.stopVideoRecording(scenario);
        }
        AppiumTest.closeApp();
    }

    @Before

    public static void beforeScenario() {
//        if(isAppAlreadyLaunched){
//            isAppAlreadyLaunched=true;
//        }else {
//            AppiumTest.launchApp();
//        }
        //ffmpeg library is not found in the path install it usig 'brew install ffmpeg' to record video on iOS
        AppiumTest.launchApp();
        if (BasePage.Android) {
            BasePage.startVideoRecording();
        }
    }
}
