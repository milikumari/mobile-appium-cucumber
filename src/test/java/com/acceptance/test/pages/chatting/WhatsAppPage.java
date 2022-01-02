package com.acceptance.test.pages.chatting;

import com.acceptance.test.pages.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

public class WhatsAppPage extends BasePage {


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.ImageButton[@content-desc='New chat']")
    })
    MobileElement newChat_Option;

    @AndroidFindAll({
            @AndroidBy(xpath="//*[@class='android.widget.TextView'][@text='WhatsApp']")
    })
    MobileElement pageTitle;

    public WhatsAppPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void selectNewChatOption() {

        clickOn_(newChat_Option);
    }


    public void confirmWhatsAppChatScreenIsDisplayed() {
        assertTrue(pageTitle.getText().equalsIgnoreCase("WhatsApp"));
    }

    public ContactChatPage initiateChat() {
        if (isElementVisible(newChat_Option)) {
            selectNewChatOption();
        }
            return new ContactChatPage();
    }

}
