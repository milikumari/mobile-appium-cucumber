package com.acceptance.test.pages.contact;

import com.acceptance.test.pages.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage extends BasePage {

    @AndroidFindAll({
            @AndroidBy(id = "TBC")
    })
    MobileElement page_TitleName;

    @AndroidFindAll({
            @AndroidBy(xpath = "//TBC")
    })
    MobileElement lastName_TextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@package='com.android.contacts'][@class='android.widget.Button'][@text='SAVE']")
    })
    MobileElement save_Option;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@package='com.android.contacts'][@class='android.widget.EditText'][@text='First name']")
    })
    MobileElement name_TextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@package='com.android.contacts'][@class='android.widget.EditText'][@text='Phone']")
    })
    MobileElement mobileNumber_TextField;

    public CreateContactPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }




    public void provideContactDetails(String name , String mobileNumber) {
        enterName(name);
        enterMobileNumber(mobileNumber);
        createContact();
    }

    private void enterName(String name){
        enterValueInToField(name_TextField, name);
    }

    private void enterMobileNumber(String mobileNumber){
        enterValueInToField(mobileNumber_TextField,mobileNumber);
    }

    private void createContact() {
        clickOn_(save_Option);
    }


}
