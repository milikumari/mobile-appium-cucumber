package com.acceptance.test.pages.registration;

import com.acceptance.test.pages.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import static com.acceptance.test.pages.BasePage.isRunningOnAndroidEmulator;
import static org.junit.Assert.assertTrue;

public class VerifyingYourNumberPage extends BasePage {

    @AndroidBy(id = "verify_sms_code_input")
    MobileElement enterOtp_textField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@class='android.widget.Button'][@text='Allow")
    })
    MobileElement allow_ButtonONPopUpWindow;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@class='android.widget.Button'][@text='Deny")
    })
    MobileElement deny_ButtonONPopUpWindow;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@class='android.widget.Button'][@text='CONTINUE")
    })
    MobileElement continue_ButtonONPopUpWindow;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@class='android.widget.Button'][@text='NOT NOW")
    })
    MobileElement notNow_ButtonONPopUpWindow;


    @AndroidFindAll({
            @AndroidBy(id="title_toolbar_text")
    })
    MobileElement page_TitleName;

    public VerifyingYourNumberPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private void enterOtp(String otpValue) {
        //this  need to be manually entered if using simulator or automatic otp is not provided
        enterValueInToField(enterOtp_textField, otpValue);
    }

    private void allowAccessToMediaPhotoAndFiles() {
        clickOn_(allow_ButtonONPopUpWindow);
    }

    private void denyAccessToMediaPhotoAndFiles() {
        clickOn_(deny_ButtonONPopUpWindow);
    }

    private void allowAccessToContact() {
        clickOn_(allow_ButtonONPopUpWindow);
    }

    private void denyAccessToContact() {
        clickOn_(deny_ButtonONPopUpWindow);
    }

    private void continueWithAccessToContactAndMedia(){
        clickOn_(continue_ButtonONPopUpWindow);
    }

    private void continueWithoutAccessToContactAndMedia(){
        clickOn_(notNow_ButtonONPopUpWindow);
    }
    public ProfileInfoPage provideAndConfirmOtp() {
      if(isRunningOnAndroidEmulator) {
          //TODO - use Twilio api (if running on emulator) or read notification from devices if you do not want whats app to read automatically
         // enterOtp(getOTP);
      }
        continueWithAccessToContactAndMedia();
        allowAccessToContact();
        denyAccessToMediaPhotoAndFiles();
        return  goToProfileInfoPage();
    }

    private ProfileInfoPage goToProfileInfoPage() {
        assertTrue(page_TitleName.getText().equalsIgnoreCase("Profile info"));
        return new ProfileInfoPage();
    }

}
