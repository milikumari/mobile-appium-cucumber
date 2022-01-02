package com.acceptance.test.stepdefinitions;

import com.acceptance.test.pages.registration.EnterYourPhoneNumberPage;
import com.acceptance.test.pages.registration.ProfileInfoPage;
import com.acceptance.test.pages.registration.VerifyingYourNumberPage;
import com.acceptance.test.pages.registration.WelcomeToWhatsAppPage;
import com.acceptance.test.utils.ScenarioProvider;
import com.acceptance.test.utils.TestDataProvider;
import io.cucumber.java.en.Given;

public class RegisterUserSteps extends  BaseSteps{

    private TestDataProvider testDataProvider;
    public RegisterUserSteps(TestDataProvider testDataProvider, ScenarioProvider scenarioProvider) {
        super(scenarioProvider);
        this.testDataProvider = testDataProvider;
    }
    WelcomeToWhatsAppPage onRegisterUserPage = new WelcomeToWhatsAppPage();
    EnterYourPhoneNumberPage onEnterYourPhoneNumberPage = new EnterYourPhoneNumberPage();
    VerifyingYourNumberPage onVerifyingYourNumberPage = new VerifyingYourNumberPage();
    ProfileInfoPage onProfileInfoPage = new ProfileInfoPage();

    @Given("I register with a user on whatsapp")
    public void i_register_with_a_user_on_whatsapp() throws InterruptedException {
        onRegisterUserPage.acceptTermsAndCondition();
        onEnterYourPhoneNumberPage.registerNumber(testDataProvider.getUser().getCountryCode(), testDataProvider.getUser().getWhatsAppNumberToRegister());
        onVerifyingYourNumberPage.provideAndConfirmOtp();
        onProfileInfoPage.provideProfileInfoAndCompleteRegistration(testDataProvider.getUser().getProfileName());
    }

}
