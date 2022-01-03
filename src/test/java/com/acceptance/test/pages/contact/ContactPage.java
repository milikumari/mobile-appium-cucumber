package com.acceptance.test.pages.contact;

import com.acceptance.test.pages.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class ContactPage extends BasePage {

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[contains(@text,'Message ')]")
    })
    private MobileElement whatsAppMessage_Option;

    @AndroidFindAll({
            @AndroidBy(xpath = "//TBC")
    })
    private MobileElement contactPersonName;

    @AndroidFindAll({
            @AndroidBy(xpath = "//TBC")
    })
    private MobileElement whatsAppVoiceCall_Option;

    @AndroidFindAll({
            @AndroidBy(xpath = "//TBC")
    })
    private MobileElement whatsAppVideoCall_Option;

    @AndroidFindAll({
            @AndroidBy(xpath = "//TBC")
    })
    private MobileElement voiceCall_Option;

    @AndroidFindAll({
            @AndroidBy(xpath = "//TBC")
    })
   private MobileElement sms_Option;

    public ContactPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void selectWhatsAppMessageOption() {

        clickOn_(whatsAppMessage_Option);
    }
}
