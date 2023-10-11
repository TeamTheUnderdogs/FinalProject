package test.cases.weare;

import com.weare.testframework.Driver;
import com.weare.testframework.UserActions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.opentest4j.AssertionFailedError;
import pages.weare.HomePage;
import org.openqa.selenium.WebDriver;

import com.weare.testframework.Utils;
import pages.weare.LoginPage;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static com.weare.testframework.Utils.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HomePageTests extends BaseTest {
    HomePage homePage = new HomePage(actions.getDriver());

    @Test
    public void anonymousSeesLoginButton_when_onHomepage() {

        homePage.assertLoginButtonExists();
    }

    @Test
    public void anonymousSeesRegisterButton_when_onHomepage() {

        homePage.assertRegisterButtonExists();
    }

    @Test
    public void anonymousSeesNoProfileButton_when_onHomepage() {
        // TODO: ensure logged out

        AssertionFailedError thrown = assertThrows(AssertionFailedError.class, homePage::assertProfileButtonExists);

        String message = String.format("Element with locator: '%s' was not found.",
                Utils.getUIMappingByKey("homePage.profile.button"));
        assertEquals(message, thrown.getMessage());
        System.out.println(message);
    }

    @Test
    public void UserSeesProfileButton_when_LoggedIn() {

        homePage.assertProfileButtonExists();

    }

@Test
    public void searchUserByCategory_as_AnonymousUser(){

        homePage.searchUserByCategory();
    Assertions.assertEquals(getConfigPropertyByKey("social.app.searchUsers.page"), getWebDriver().getCurrentUrl() , "Page not successfully navigated");

}

}
