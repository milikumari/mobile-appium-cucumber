package com.acceptance.test.pages.contact;

import com.acceptance.test.pages.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;

public class SelectContactPage extends BasePage {


    @iOSXCUITFindAll({
            @iOSXCUITBy(id = "addElementIdForIos")})

    @AndroidFindAll({
            @AndroidBy(id = "com.whatsapp:id/contactpicker_row_name"),
            @AndroidBy(xpath = "//*[@class='android.widget.TextView'][@text='New group']")
    })
    MobileElement newGroup;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@class='android.widget.TextView'][@text='New contact']")
    })
    MobileElement newContact;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@class='android.widget.TextView'][@text='No WhatsApp contacts']")
    })
    MobileElement  noWhatsAppContacts;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.FrameLayout[2]/android.widget.ListView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView")
    })
    MobileElement firstContact;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@class='android.widget.TextView'][@text='Invite friends']")
    })
    MobileElement inviteFriends;

    @AndroidFindAll({
            @AndroidBy(id = "com.whatsapp:id/contact_picker_help_text_container"),
            @AndroidBy(xpath = "//*[@class='android.widget.TextView'][@text='Contacts help']")

    })
    MobileElement contactHelp;

    public SelectContactPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void selectNewGroupOption() {
        clickOn_(newGroup);
    }

    public CreateContactPage selectNewContactOption() {

        clickOnNewContactOption();
        return new CreateContactPage();
    }

    public void selectInviteFriendsOption() {
        clickOn_(inviteFriends);
    }

    public void selectContactsHelpOption() {
        clickOn_(contactHelp);
    }

    private  void clickOnNewContactOption(){
        clickOn_(newContact);
    }

    public boolean noContactsFound() {
        return checkIfWhatsAppContactExist();
    }

    private boolean checkIfWhatsAppContactExist(){
    return isElementVisible(noWhatsAppContacts);
    }

    public void selectFirstContactFromTheList(String contactName) {
        selectFistContact(contactName);
    }

    private void selectFistContact(String contactName) {
   //this xpath can be modified to select based on teh name of contact person you want to chat with
       // MobileElement contactNameElement= (MobileElement) driver.findElement(By.xpath
       //         ("//*[@class='android.widget.TextView'][@text='"+ contactName+ "']"));
        //clickOn_(contactNameElement);
        clickOn_(firstContact);
    }
}
