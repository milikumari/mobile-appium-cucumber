package com.acceptance.test.pages.chatting;

import com.acceptance.test.pages.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.assertj.core.api.Fail;
import org.openqa.selenium.support.PageFactory;

import static com.acceptance.test.pages.BasePage.isElementVisible;

public class ContactChatPage extends BasePage {
    @AndroidFindAll({
            @AndroidBy(id = "com.whatsapp:id/input_attach_button")
    })
    MobileElement attachment_Link;

    @AndroidFindAll({
            @AndroidBy(id = "com.whatsapp:id/pickfiletype_document_holder")
    })
    MobileElement document_Option;

    @AndroidFindAll({
            @AndroidBy(id = "com.whatsapp:id/pickfiletype_camera_holder")
    })
    MobileElement camera_Option;

    @AndroidFindAll({
            @AndroidBy(id = "com.whatsapp:id/pickfiletype_gallery_holder")
    })
    MobileElement gallery_Option;

    @AndroidFindAll({
            @AndroidBy(id = "com.whatsapp:id/pickfiletype_audio_holder")
    })
    MobileElement audio_Option;

    @AndroidFindAll({
            @AndroidBy(id = "com.whatsapp:id/pickfiletype_location_holder")
    })
    MobileElement location_Option;
    @AndroidFindAll({
            @AndroidBy(xpath = "com.whatsapp:id/pickfiletype_contact_holder")
    })
    MobileElement contact_Option;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.ImageView[@content-desc=\"Delivered\" or @content-desc=\"Seen\" ]")
    })
    MobileElement status_Delivered_OR_Seen_Indicator;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.ImageView[@content-desc=\"Delivered\" or @content-desc=\"Seen\" ]")
    })
    MobileElement status_Pending_Indicator;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.ImageView[@content-desc=\"Delivered\" or @content-desc=\"Seen\" ]")
    })
    MobileElement secondDocumentFromList;

    public ContactChatPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public BasePage selectAttachmentType(String attachmentType) {
        switch (attachmentType.toLowerCase()) {
            case "documents":
                selectDocumentOption();
                break;
                //TO DO implement other options
            // case "camera":
            // case"gallery":
            // case "audio":
            // break;
            default:
                Fail.fail("please provide valid option currently only document option is implemented ");

        }
        return  new BasePage();
    }

    public void selectAttachmentLink() {
        clickOnAttachment();
    }

    public void clickOnAttachment() {
        clickOn_(attachment_Link);
    }


    private DocumentsPage selectDocumentOption() {
        clickOn_(document_Option);
       return new DocumentsPage();
    }

    public void FileIsSentToContact() {
        isElementVisible(status_Delivered_OR_Seen_Indicator);
    }
}
