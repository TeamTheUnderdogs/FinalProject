package test.cases.weare.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.weare.LoginPage;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static com.weare.testframework.Utils.getWebDriver;

public class LoginTests extends BaseTest {

    LoginPage loginPage = new LoginPage(actions.getDriver());

    @Test

    public void loginUser_when_validCredentialsProvided() {
        loginPage.loginUser();
        actions.assertElementPresent("homePage.logout.button");
    }

    @Test
    public void loginAdmin_when_validCredentialsProvided() {
        loginPage.loginAdmin();
        actions.assertElementPresent("homePage.logout.button");
    }

    @Test
    public void logoutUser_when_logoutButtonClicked() {
        loginPage.logoutUser();

        Assertions.assertEquals(getConfigPropertyByKey("social.app.logoutPage"),
                getWebDriver().getCurrentUrl(), "Page not successfully navigated");
    }

    @Test
    public void logoutAdmin_when_logoutButtonClicked() {
        loginPage.logoutAdmin();

        Assertions.assertEquals(getConfigPropertyByKey("social.app.logoutPage"),
                getWebDriver().getCurrentUrl(), "Page not successfully navigated");
    }


}
