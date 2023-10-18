package test.cases.weare.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import pages.weare.HomePage;

import com.weare.testframework.Utils;
import pages.weare.LoginPage;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static com.weare.testframework.Utils.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HomePageTests extends BaseTest {


    @Test
    public void anonymousSeesLoginButton_when_onHomepage() {

       actions.assertElementPresent("homePage.login.button");
    }

    @Test
    public void anonymousSeesRegisterButton_when_onHomepage() {

        actions.assertElementPresent("homePage.register.button");
    }


@Test
    public void searchUserByCategory_as_AnonymousUser(){

homepage = new HomePage(actions.getDriver());
homepage.searchUserByCategory();
    Assertions.assertEquals(getConfigPropertyByKey("social.app.searchUsers.page"), getWebDriver().getCurrentUrl() ,
            "Page not successfully navigated");

    Assertions.assertEquals(getConfigPropertyByKey("social.app.searchUsers.page"),
            getWebDriver().getCurrentUrl() , "Page not successfully navigated");



}

}