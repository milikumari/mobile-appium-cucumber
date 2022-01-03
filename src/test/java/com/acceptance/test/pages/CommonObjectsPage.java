package com.acceptance.test.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;

public class CommonObjectsPage extends BasePage{

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@class='android.widget.Button'][@text='Allow")
    })
   protected MobileElement allow_ButtonONPopUpWindow;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@class='android.widget.Button'][@text='Deny")
    })
    protected MobileElement deny_ButtonONPopUpWindow;
}
