package com.acceptance.test.pages.chatting;

import com.acceptance.test.pages.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class DocumentsPage extends BasePage {

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='com.whatsapp:id/document_picker_item'][@index='2']")
    })
    private MobileElement secondItemFromDocuments_List;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='SEND'][@content-desc='Send']")
    })
   private MobileElement send_Button_OnPopUpWindow;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='CANCEL'][@content-desc='Cancel']")
    })
    private MobileElement cancel_Button_OnPopUpWindow;

    public DocumentsPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void sendFileFromTheList() {
 // TO Do this element-> 'secondItemFromDocuments_List'
        // can be dynamic to pick up specific file or place from list currently this is
        // written for picking second item from the list
        clickOn_(secondItemFromDocuments_List);
        clickOn_(send_Button_OnPopUpWindow);
    }
}
