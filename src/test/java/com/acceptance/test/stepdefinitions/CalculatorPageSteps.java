package com.acceptance.test.stepdefinitions;

import com.acceptance.test.pages.CalculatorPage;
import com.acceptance.test.testDataModel.TestDataContext;
import com.acceptance.test.utils.RuntimeState;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorPageSteps {
    private CalculatorPage calculatorPage= new CalculatorPage();
    private RuntimeState  runtimeState;

    public CalculatorPageSteps(RuntimeState runtimeState){
        this.runtimeState=runtimeState;
    }

    @Given("Calculator app is open")
    public void i_calculator_app_is_open() {
      calculatorPage.validateCalculatorPageIsOpen();    }


    @When("I add {int} and {int}")
    public void i_add_and(Integer num1, Integer num2) {
        TestDataContext.actualResult =calculatorPage.addTwoSingleDigits(num1,num2);

    }
    @Then("sum should be {string}")
       public void sum_should_be(String expectedResult) {
        calculatorPage.validateResult(expectedResult,TestDataContext.actualResult);

    }
}
