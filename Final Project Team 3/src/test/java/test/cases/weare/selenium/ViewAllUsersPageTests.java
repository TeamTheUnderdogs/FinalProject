package test.cases.weare.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.weare.ViewAllUsersPage;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static com.weare.testframework.Utils.getWebDriver;

public class ViewAllUsersPageTests extends BaseTest {



    @Test

    public void adminSuccessfullyViewAllUsers_when_logedin(){
        loginPage.loginAdmin();
        viewAllUsersPage.navigateToPage();
        Assertions.assertEquals(getConfigPropertyByKey("social.app.viewAllUsersPage"), getWebDriver().getCurrentUrl(),
                "Page not successfully navigated");
    }
    @Test
    public void adminSuccessfullyViewUserProfile_when_logedin(){
        loginPage.loginAdmin();
        viewAllUsersPage.navigateToPage();
        viewAllUsersPage.adminExploreUserProfile();

        actions.assertElementPresent("userPage.disableProfile.button");
    }
}
