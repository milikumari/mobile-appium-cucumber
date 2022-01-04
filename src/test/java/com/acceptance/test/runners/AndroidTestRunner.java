package com.acceptance.test.runners;


import com.acceptance.test.AppiumTest;
import com.acceptance.test.utils.PropertyLoader;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                publish = false,
                glue = {"com.acceptance.test.stepdefinitions"},
                tags = "@whatsAppChat"
                , features = "src/test/resources/features"
                , plugin = {"json:target/report/cucumber-json.json", "html:target/report/cucumber-report.html",
                "de.monochromata.cucumber.report.PrettyReports:target/AndroidReports",
                "pretty"}
        )
public class AndroidTestRunner {

    @BeforeClass
    public static void setupServer() {
        PropertyLoader.loadConfig();
        // TODO add step to delete Appiumlog.txt
        AppiumTest.setUp();
    }

    @AfterClass

    public static void tearDownServer() {
        AppiumTest.tearDown();
    }

    private static void setTestEnv() {
        System.setProperty("testEnv", "local");

    }
}