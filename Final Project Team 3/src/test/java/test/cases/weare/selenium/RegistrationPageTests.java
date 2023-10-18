package test.cases.weare.selenium;


import org.junit.jupiter.api.Test;
import pages.weare.LoginPage;
import pages.weare.RegistrationPage;

public class RegistrationPageTests extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage(actions.getDriver());

    @Test

    public void successfullyRegisterUser_when_validCredentialsProvided() {


        registrationPage.registerUser();

        actions.assertElementPresent("registrationPage.updateYouProfile.button");
        LoginPage loginPage = new LoginPage(actions.getDriver());

    }

    @Test
    public void successfullyRegisterAdmin_when_validCredentialsProvided() {


        registrationPage.registerAdmin();
        actions.assertElementPresent("userPage.disableProfile.button");

    }
}
