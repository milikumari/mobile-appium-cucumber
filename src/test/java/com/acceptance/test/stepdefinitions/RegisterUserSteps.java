package com.acceptance.test.stepdefinitions;

import com.acceptance.test.pages.registration.EnterYourPhoneNumberPage;
import com.acceptance.test.pages.registration.ProfileInfoPage;
import com.acceptance.test.pages.registration.VerifyingYourNumberPage;
import com.acceptance.test.pages.registration.WelcomeToWhatsAppPage;
import io.cucumber.java.en.Given;

public class RegisterUserSteps {

    WelcomeToWhatsAppPage onRegisterUserPage = new WelcomeToWhatsAppPage();
    EnterYourPhoneNumberPage onEnterYourPhoneNumberPage = new EnterYourPhoneNumberPage();
    VerifyingYourNumberPage onVerifyingYourNumberPage = new VerifyingYourNumberPage();
    ProfileInfoPage onProfileInfoPage = new ProfileInfoPage();

    @Given("I register with a user on whatsapp")
    public void i_register_with_a_user_on_whatsapp() throws InterruptedException {
        onRegisterUserPage.acceptTermsAndCondition();
        onEnterYourPhoneNumberPage.registerNumber("+44", "7926421289");
        onVerifyingYourNumberPage.provideAndConfirmOtp();
        onProfileInfoPage.provideProfileInfoAndCompleteRegistration("contactName-1");
    }

}
