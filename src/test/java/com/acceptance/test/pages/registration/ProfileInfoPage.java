package com.acceptance.test.pages.registration;

import com.acceptance.test.pages.BasePage;
import com.acceptance.test.pages.chatting.WhatsAppPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

public class ProfileInfoPage extends BasePage {

    @AndroidFindAll({
            @AndroidBy(id="title_toolbar_text")
    })
    MobileElement pageTitle;
    @AndroidFindAll({
            @AndroidBy(id = "registration_submit"),
            @AndroidBy(xpath = "//*[@text='NEXT']")
    })
    MobileElement next_Button;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@class='android.widget.EditText']")
    })
    MobileElement profileName_TextField;

    public ProfileInfoPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private void enterProfileName(String name) {

        enterValueInToField(profileName_TextField, name);
    }

    private void clickOnNextButton() {
        clickOn_(next_Button);
    }

    public WhatsAppPage provideProfileInfoAndCompleteRegistration(String name) {
        enterProfileName(name);
        clickOnNextButton();
        return confirmRegistrationCompleted();
    }

    private WhatsAppPage confirmRegistrationCompleted(){
        assertTrue(pageTitle.getText().equalsIgnoreCase("WhatsApp"));
        return new WhatsAppPage();
    }
}
