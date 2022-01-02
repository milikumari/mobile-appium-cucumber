package com.acceptance.test.pages.registration;

import com.acceptance.test.pages.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

public class EnterYourPhoneNumberPage extends BasePage {


    @AndroidFindAll({
            @AndroidBy(id="title_toolbar_text")
    })
    MobileElement pageTitle;
    @AndroidFindAll({
            @AndroidBy(id = "registration_country")
    })
    MobileElement registrationCountySelection_DropDown;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@class='android.widget.TextView'][@text='+44']")
    })
    MobileElement selectUk_CountryOption;

    @AndroidFindAll({
            @AndroidBy(id = "registration_phone"),
            @AndroidBy(xpath = "//*[@class='android.widget.EditText'][@text='phone number']")
    })
    MobileElement mobileNumber_TextField;

    @AndroidFindAll({
            @AndroidBy(id = "registration_submit"),
            @AndroidBy(xpath = "//*[@class='android.widget.Button'][@text='NEXT']")
    })
    MobileElement next_Button;

    @AndroidFindAll({
            @AndroidBy(id = "button1"),
            @AndroidBy(xpath = "//*[@text='OK']")
    })
    MobileElement ok_ButtonToConfirmNumber;

    @AndroidFindAll({
            @AndroidBy(id = "button3"),
            @AndroidBy(xpath = "//*[@text='EDIT']")
    })
    MobileElement edit_ButtonToAmendNumber;

    @AndroidFindAll({
            @AndroidBy(id = "cancel"),
            @AndroidBy(xpath = "//*[@class='android.widget.Button'][@text='Not Now']")
    })
    MobileElement notNow_Button;

    @AndroidFindAll({
            @AndroidBy(id = "submit"),
            @AndroidBy(xpath = "//*[@class='android.widget.Button'][@text='CONTINUE']")
    })
    MobileElement continue_Button;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@class='android.widget.Button'][@text='DENY']")
    })
    MobileElement deny_Button;
    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@class='android.widget.Button'][@text='ALLOW']")
    })
    MobileElement allow_Button;


    public EnterYourPhoneNumberPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public VerifyingYourNumberPage registerNumber(String countryCode, String mobileNumber) {
        clickOn_(registrationCountySelection_DropDown);
        clickOn_(selectUk_CountryOption);
        enterValueInToField(mobileNumber_TextField, mobileNumber);
        clickOnNextButton();
        confirmNumber();
       if(isRunningOnAndroidEmulator){
        doNotAllowWhatsAppToAutomaticallyVerifyOTP();}
        else{
            allowWhatsAppToAutomaticallyVerifyOTP();
            allowWhatsAppToAccessSms();
        }
        return goToVerifyingYourNumberPage();
    }

    private VerifyingYourNumberPage goToVerifyingYourNumberPage() {
        //assertTrue("expected Page with title name not displayed", pageTitle.getText().equals("Verifying your number"));
        return new VerifyingYourNumberPage();
    }

    private void denyWhatsAppToAccessSms(){
        clickOn_(deny_Button); // if you want whatsApp to verify OTP automatically read.
    }

    private void allowWhatsAppToAccessSms() {
        clickOn_(allow_Button);

    }

    private void allowWhatsAppToAutomaticallyVerifyOTP(){
        if(isElementVisible(continue_Button)) {
            clickOn_(continue_Button);
        }
    }
    private void doNotAllowWhatsAppToAutomaticallyVerifyOTP(){
        clickOn_(notNow_Button); // if you want whatsApp to verify OTP automatically read.
    }
    private void clickOnNextButton(){
        clickOn_(next_Button); // if you want whatsApp to verify OTP automatically read.
    }

    private void confirmNumber(){
        clickOn_(ok_ButtonToConfirmNumber); // if you want whatsApp to verify OTP automatically read.
    }
}
