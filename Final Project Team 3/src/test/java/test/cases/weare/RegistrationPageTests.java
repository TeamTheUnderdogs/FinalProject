package test.cases.weare;

import Models.User;
import com.weare.testframework.UserActions;
import factories.UserFactory;
import org.junit.jupiter.api.Test;
import pages.weare.RegistrationPage;

public class RegistrationPageTests extends BaseTest{

    RegistrationPage registrationPage = new RegistrationPage(actions.getDriver());

    @Test

    public void successfullyRegisterUser_when_validCredentialsProvided () {


        registrationPage.registerUser();
        UserActions userActions = new UserActions();
        userActions.assertElementPresent("registrationPage.updateYouProfile.button");
    }

    @Test
    public void successfullyRegisterAdmin_when_validCredentialsProvided () {


        registrationPage.registerAdmin();
        UserActions userActions = new UserActions();
        userActions.assertElementPresent("userPage.disableProfile.button");
    }
}
