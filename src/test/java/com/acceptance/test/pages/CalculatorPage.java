    package com.acceptance.test.pages;

    import io.appium.java_client.MobileElement;
    import io.appium.java_client.pagefactory.*;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.PageFactory;

    import java.util.List;

    import static junit.framework.TestCase.assertEquals;
    import static junit.framework.TestCase.assertTrue;

    public class CalculatorPage extends BasePage{

    @iOSXCUITFindAll ({
            @iOSXCUITBy(id="addElementId"),
            @iOSXCUITBy(xpath="//*")})

    @AndroidFindAll({
            @AndroidBy(id="addElementd"),
            @AndroidBy(xpath="//android.widget.TextView")

    })

    MobileElement calculator_homePage;
        @AndroidFindBy(id="digit_0")
        MobileElement digit_0;
        @AndroidFindBy(id="digit_1")
        MobileElement digit_1_button;

        @iOSXCUITFindBy(id="digit_2")
            @AndroidFindBy(id="digit_2")
    MobileElement digit_2_button;

        @AndroidFindBy(id="digit_3")
        MobileElement digit_3_button;

        @AndroidFindBy(id="digit_4")
        MobileElement digit_4_button;

        @AndroidFindBy(id="digit_5")
        MobileElement digit_5_button;

        @AndroidFindBy(id="digit_6")
        MobileElement digit_6_button;

        @AndroidFindBy(id="digit_7")
        MobileElement digit_7_button;

        @AndroidFindBy(id="digit_8")
        MobileElement digit_8_button;

        @AndroidFindBy(id="digit_9")
        MobileElement digit_9_button;

        @AndroidFindBy(id="op_add")
        MobileElement plus_button;

        @AndroidFindBy(accessibility="equals")
        @AndroidFindBy(id="eq")
        MobileElement equal_button;

        @AndroidFindBy(id="op_mul")
        MobileElement multiply_button;

        @AndroidFindBy(id="op_div")
        MobileElement division_button;

        @AndroidFindBy(id="op_sub")
        MobileElement minus_button;

        @AndroidFindBy(id="del")
        MobileElement delete_button;

        @AndroidFindBy(accessibility="percent")
        @AndroidFindBy(id="op_pct")
        MobileElement percentage_button;

        @AndroidFindBy(accessibility="clear")
        @AndroidFindBy(id="clr")
        MobileElement Ac_clear_button;

        @AndroidFindBy(accessibility="point")
        @AndroidFindBy(id="dec_point")
        MobileElement decimal_point_button;


        @AndroidFindBy(accessibility="left parenthesis")
        @AndroidFindBy(id="lparen")
        MobileElement left_parenthesis_button;

        @AndroidFindBy(id="result")
        MobileElement result;

        @AndroidFindBy(xpath="//*[contains(@id,\"digit_\")]")
        List<MobileElement> digits_button;



        public CalculatorPage(){
            PageFactory.initElements(new AppiumFieldDecorator( driver), this);
        }

        public void validateCalculatorPageIsOpen() {
            checkCalculatorPageIsDisplayed();
        }

        private void checkCalculatorPageIsDisplayed() {
            assertTrue("",calculator_homePage.isDisplayed());
            assertTrue("expecting to see Calculator name",calculator_homePage.getText().equals("Calculator"));
        }

        private int addDigits(List<Integer> integerList){
            return integerList.stream().mapToInt(Integer::intValue).sum();
        }

        public String addTwoSingleDigits(int num1 ,int num2){
            getDigitElement( num1).click();
            plus_button.click();
            getDigitElement(num2).click();
            equal_button.click();
            result.getText();
            return String.valueOf(num1+num2);
        }


        private WebElement getDigitElement(int num1){
            //return digits_button.stream().filter(e -> e.getText().equalsIgnoreCase(String.valueOf(num1))).findFirst().get();
          return driver.findElement(By.id("digit_"+String.valueOf(num1)));
        }

        public void validateResult(String expectedResult, String actualResult) {

            assertEquals("expected result don't match with actualResult value",expectedResult,actualResult);


        }
    }

