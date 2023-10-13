package test.cases.weare;

import com.weare.testframework.UserActions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.weare.HomePage;
import pages.weare.LoginPage;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static com.weare.testframework.Utils.getWebDriver;

public class LoginPageTests extends BaseTest {

    LoginPage loginPage = new LoginPage(actions.getDriver());
    UserActions userActions = new UserActions();
    @Test

    public void loginUser_when_validCredentialsProvided () {

        loginPage.loginUser();
        userActions.assertElementPresent("homePage.logout.button");
    }
@Test
    public void loginAdmin_when_validCredentialsProvided () {

        loginPage.loginAdmin();
        userActions.assertElementPresent("homePage.logout.button");
    }
    @Test
     public void logoutUser_Successfully(){
        loginPage.logoutUser();

        Assertions.assertEquals(getConfigPropertyByKey("social.app.logoutPage"),
                 getWebDriver().getCurrentUrl() , "Page not successfully navigated");
     }

    @Test
    public void logoutAdmin_Successfully(){
        loginPage.logoutAdmin();

        Assertions.assertEquals(getConfigPropertyByKey("social.app.logoutPage"),
                getWebDriver().getCurrentUrl() , "Page not successfully navigated");
    }


}
