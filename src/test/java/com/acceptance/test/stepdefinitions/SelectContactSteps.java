package com.acceptance.test.stepdefinitions;

import com.acceptance.test.pages.chatting.ContactChatPage;
import com.acceptance.test.pages.chatting.DocumentsPage;
import com.acceptance.test.pages.chatting.WhatsAppPage;
import com.acceptance.test.pages.newContact.CreateContactPage;
import com.acceptance.test.pages.newContact.SelectContactPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SelectContactSteps {

    SelectContactPage onSelectContactPage = new SelectContactPage();
    CreateContactPage onCreateContactPage = new CreateContactPage();
    WhatsAppPage onWhatsAppPage = new WhatsAppPage();
    ContactChatPage onContactChatPage = new ContactChatPage();
    DocumentsPage onDocumentsFileSelectionPage = new DocumentsPage();

    @When("I navigate to whatsapp chat screen")
    public void i_navigate_to_whatsapp_chat_screen() {
        onWhatsAppPage.confirmWhatsAppChatScreenIsDisplayed();
    }

    @When("I tap on new chat icon")
    public void i_tap_on_new_chat_icon() {
        onWhatsAppPage.selectNewChatOption();
      //  onCreateContactPage.provideContactDetails("test1", "00447921880054");
    }

    @When("I send a random file to that contact")
    public void i_send_a_random_file_to_that_contact() {
        onWhatsAppPage.initiateChat();
        onContactChatPage.selectAttachmentLink();
        onContactChatPage.selectAttachmentType("documents");
        // This below step will need to be modified if different type of attachment is selected
        //sendFile("attachmentType");
        onDocumentsFileSelectionPage.sendFileFromTheList();
    }

    private  void sendFile(String attachmentType){

        switch (attachmentType.toLowerCase()){

            case"documents":
                onDocumentsFileSelectionPage.sendFileFromTheList();
                break;
            case "location":
                //TO DO
                break;
            case "gallery":
                //TO DO
             //   onGalleryPage().sendFileFromTheList()
                break;
        }
    }

    @Then("I should see the random file sent to that contact successfully.")
    public void i_should_see_the_random_file_sent_to_that_contact_successfully() {
        onContactChatPage.FileIsSentToContact();
    }

    @When("I add new contact")
    public void i_add_new_contact() {
        onSelectContactPage.selectNewContactOption();
        onCreateContactPage.provideContactDetails("","007921880054");
    }
    @When("I select a contact from the list")
    public void i_select_a_contact_from_the_list() {
        if(onSelectContactPage.noContactsFound()){
            i_add_new_contact();
            boolean isNewContactAdded = true;
        }else
            onSelectContactPage.selectFirstContactFromTheList("contactName");
    }
}
