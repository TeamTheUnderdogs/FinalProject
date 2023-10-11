package test.cases.weare;

import com.weare.testframework.UserActions;
import org.junit.jupiter.api.Test;
import pages.weare.HomePage;
import pages.weare.LoginPage;

public class LoginPageTests extends BaseTest {

    LoginPage loginPage = new LoginPage(actions.getDriver());
    @Test

    public void loginUser_when_validCredentialsProvided () {

        loginPage.loginUser();
        UserActions userActions = new UserActions();
        userActions.assertElementPresent("homePage.logout.button");
    }
}
