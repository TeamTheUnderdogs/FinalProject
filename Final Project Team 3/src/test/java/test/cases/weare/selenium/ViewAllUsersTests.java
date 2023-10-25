package test.cases.weare.selenium;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.weare.LoginPage;
import pages.weare.ViewAllUsersPage;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static com.weare.testframework.Utils.getWebDriver;
public class ViewAllUsersTests extends BaseTest {
    LoginPage loginPage = new LoginPage(actions.getDriver());
    ViewAllUsersPage viewAllUsersPage = new ViewAllUsersPage(actions.getDriver());

    @BeforeEach
    public void setup() {
        loginPage.loginAdmin();
        viewAllUsersPage.navigateToPage();
    }

    @Test
    public void adminSuccessfullyViewAllUsers_when_loggedIn() {
//        loginPage.loginAdmin();
//        viewAllUsersPage.navigateToPage();
        Assertions.assertEquals(getConfigPropertyByKey("social.app.viewAllUsersPage"),
                getWebDriver().getCurrentUrl(), "Page not successfully navigated");
    }
    @Test
    public void adminSuccessfullyViewUserProfile_when_LoggedIn() {
//        loginPage.loginAdmin();
//        viewAllUsersPage.navigateToPage();
        viewAllUsersPage.adminExploreUserProfile();
        actions.assertElementPresent("userPage.disableProfile.button");
    }
}
