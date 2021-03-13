package com.acceptance.test.runners;


import com.acceptance.test.AppiumTest;
import com.acceptance.test.utils.LoadProperties;
import com.acceptance.test.utils.PropertyLoader;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                publish=false,
                glue = { "com.acceptance.test.stepdefinitions"},
                tags = "@cucumberTest"
                ,features =  "src/test/resources/features"
                ,plugin={"json:target/report/cucumber-json.json","html:target/report/cucumber-html.html",
                "de.monochromata.cucumber.report.PrettyReports:target/AndroidReports",
                "pretty"}
        )
public class AndroidTestRunner {

    @BeforeClass
    public  static  void setupServer(){
        PropertyLoader.loadConfig();
        AppiumTest.setUp();
    }

    @AfterClass

    public static  void tearDownServer() {
        AppiumTest.tearDown();
    }

    private static  void setTestEnv(){
        System.setProperty("testEnv", "brokerSim");

    }
}


