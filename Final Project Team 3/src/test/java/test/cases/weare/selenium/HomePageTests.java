package test.cases.weare.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.weare.HomePage;
import pages.weare.LoginPage;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static com.weare.testframework.Utils.getWebDriver;

public class HomePageTests extends BaseTest {

    HomePage homePage = new HomePage(actions.getDriver());
    LoginPage loginPage = new LoginPage(actions.getDriver());

    @Test
    public void anonymousSeesLoginButton_when_onHomepage() {

        actions.assertElementPresent("homePage.login.button");
    }

    @Test
    public void anonymousSeesRegisterButton_when_onHomepage() {

        actions.assertElementPresent("homePage.register.button");
    }


    @Test
    public void searchUserByCategory_as_Anonymous() {

        homePage.searchUserByCategory();
        Assertions.assertEquals(getConfigPropertyByKey("social.app.searchUsers.page"), getWebDriver().getCurrentUrl(),
                "Page not successfully navigated");

        Assertions.assertEquals(getConfigPropertyByKey("social.app.searchUsers.page"),
                getWebDriver().getCurrentUrl(), "Page not successfully navigated");
    }

    @Test
    public void searchUserByCategory_as_User() {
        loginPage.loginUser();
        homePage.searchUserByCategory();
        Assertions.assertEquals(getConfigPropertyByKey("social.app.searchUsers.page"), getWebDriver().getCurrentUrl(),
                "Page not successfully navigated");

        Assertions.assertEquals(getConfigPropertyByKey("social.app.searchUsers.page"),
                getWebDriver().getCurrentUrl(), "Page not successfully navigated");
    }


}
