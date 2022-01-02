package com.acceptance.test.pages.registration;

import com.acceptance.test.pages.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class WelcomeToWhatsAppPage extends BasePage {
    @AndroidFindAll({
            @AndroidBy(id = "button2"),
            @AndroidBy(xpath = "//*[@text='OK']")
    })
    MobileElement installationAlertWarning_Button;

    @AndroidBy(xpath = "//android.widget.TextView[1]")
    MobileElement welcomePage_Title;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='AGREE AND CONTINUE']"),
            @AndroidBy(id = "eula_accept")
    })
    MobileElement agreeAndContinueTermAndCondition_Button;

    @AndroidBy(id = "registration_country")
    MobileElement selectCounty_DropDownOptions;

    @AndroidBy(xpath = "//*[contains(text(),'+44')]")
    MobileElement countyCodeUk_selectOption;

    @AndroidBy(id = "registration_phone")
    MobileElement enterMobileNumber_TextField;

    @AndroidBy(id = "cancel")
    MobileElement notNow_Button;


    @AndroidBy(id = "submit")
    MobileElement continue_Button;


    @AndroidBy(id = "registration_submit")
    MobileElement next_Button;

    @AndroidBy(id = "button1")
    MobileElement confirmMobileNumberAlertOK_Button;


    public WelcomeToWhatsAppPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickOnOkOnInstallationAlert() throws InterruptedException {
        Random random = new Random();

        String generatedString = random.ints(97, 122 + 1)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='New chat']")).click();
        Thread.sleep(900);
        driver.findElement(By.xpath("//*[@class='android.widget.TextView'][@index='0'][@text='New contact']")).click();
        Thread.sleep(900);
        // driver.hideKeyboard();

//        MobileElement el2 = (MobileElement) driver.findElementById("com.samsung.android.contacts:id/tw_spinner_view");
//        el2.click();
//        MobileElement el3 = (MobileElement) driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"Phone. Not selected\"]/android.widget.LinearLayout/android.widget.TextView");
//        el3.click();
        driver.findElement(By.xpath("//*[@class='android.widget.EditText'][@text='Name' or @text='First name'] ")).sendKeys(generatedString);
        driver.findElement(By.xpath("//*[@class='android.widget.EditText'][@text='Phone']")).sendKeys("00447921880054");
        driver.findElement(By.xpath("//*[@class='android.widget.Button'][@text='SAVE']")).click();
        //     driver.getKeyboard();
        Thread.sleep(2000);
        // driver.findElement(By.xpath("//*[contains(@text,'Message ')]")).click();//done
        driver.findElement(By.id("com.whatsapp:id/input_attach_button")).click();
        driver.findElement(By.id("com.whatsapp:id/pickfiletype_document_holder")).click();
        // driver.findElement(By.id("com.whatsapp:id/input_attach_button")).click();
        //selecting second item from  document list
        driver.findElement(By.xpath("//*[@resource-id='com.whatsapp:id/document_picker_item'][@index='2']")).click();

        driver.findElement(By.xpath("//*[@text='SEND'][@content-desc='Send']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Delivered\" or @content-desc=\"Seen\" ]")).isDisplayed();

        longPress();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Delete chat\"]")).click();
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Delete\"]")).click();


/*
            driver.findElement(By.className("android.widget.Button")).click();
        driver.findElement(By.id("eula_accept")).click();
        driver.findElement(By.id("registration_country")).click();
        driver.findElement(By.xpath("//android.widget.LinearLayout[5]/android.widget.LinearLayout")).click();
        driver.findElement(By.id("registration_phone")).sendKeys("7921880054");
        driver.findElement(By.id("registration_submit")).click();  //next
            driver.findElement(By.id("android:id/button1")).click(); //confirmNumber
            driver.findElement(By.id("android:id/cancel")).click(); // notNow
            driver.findElement(By.id("android:id/verify_sms_code_input")).click(); //enter


        if(installationAlertWarning_Button.isDisplayed()){
        installationAlertWarning_Button.click();
        }
        agreeAndContinueTermAndCondition_Button.click();
        selectCounty_DropDownOptions.click();
        countyCodeUk_selectOption.click();
        enterMobileNumber_TextField.sendKeys("7921880054");
        next_Button.click();
        confirmMobileNumberAlertOK_button.click();
        notNow_Button.click();
       // continue_Button.click();
            enterOtp_textField.sendKeys("343434");
            */

    }

    private void ignoreInstallationWarning() {
        clickOn_(installationAlertWarning_Button);
    }

    private void clickOnTermsAndConditionButton() {
        clickOn_(agreeAndContinueTermAndCondition_Button);
    }

    private void openListToSelectRegistrationCountry() {
        clickOn_(selectCounty_DropDownOptions);
    }

    private void selectCountry(String countryCode) {
        // xpath can be writt country code provided
        String path = "//*[contains(text(),'+" + countryCode + "')]";
        MobileElement countryCodeElement = (MobileElement) (driver.findElement(By.xpath(path)));
        //clickOn_(countryCodeElement);
        clickOn_(countyCodeUk_selectOption);
    }

    private void enterMobileNumber(String mobileNumberValue) {
        enterValueInToField(enterMobileNumber_TextField, mobileNumberValue);
    }

    private void clickOnNextButton() {
        clickOn_(next_Button);
    }

    private void confirmMobileNumber() {
        clickOn_(confirmMobileNumberAlertOK_Button);
    }


    private void selectNotNow() {
        clickOn_(notNow_Button);
    }

    private  EnterYourPhoneNumberPage goToEnterYourPhoneNumberPage(){

        return  new EnterYourPhoneNumberPage();
    }

    public  EnterYourPhoneNumberPage  acceptTermsAndCondition(){
        if(isRunningOnAndroidEmulator){
            ignoreInstallationWarning();
        }
        clickOnTermsAndConditionButton();
        return goToEnterYourPhoneNumberPage();
    }
}