package test.cases.weare.selenium;

import com.weare.testframework.UserActions;
import org.junit.jupiter.api.Test;
import pages.weare.LoginPage;
import pages.weare.RegistrationPage;

public class RegistrationPageTests extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage(actions.getDriver());

    @Test

    public void successfullyRegisterUser_when_validCredentialsProvided() {


        registrationPage.registerUser();
        UserActions userActions = new UserActions();
        userActions.assertElementPresent("registrationPage.updateYouProfile.button");
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.logoutUser();
    }

    @Test
    public void successfullyRegisterAdmin_when_validCredentialsProvided() {


        registrationPage.registerAdmin();
        actions.assertElementPresent("userPage.disableProfile.button");

    }
}
