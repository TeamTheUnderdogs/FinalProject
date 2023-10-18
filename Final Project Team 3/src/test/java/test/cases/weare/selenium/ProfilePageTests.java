package test.cases.weare.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.weare.LoginPage;
import pages.weare.ProfilePage;
import pages.weare.ViewAllUsersPage;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static com.weare.testframework.Utils.getWebDriver;

public class ProfilePageTests extends BaseTest {

    LoginPage loginPage = new LoginPage(actions.getDriver());
    ViewAllUsersPage viewAllUsersPage = new ViewAllUsersPage(actions.getDriver());
    ProfilePage profilePage = new ProfilePage(actions.getDriver());


    @Test

    public void adminSuccessfullyUpdateUserPersonalProfile_when_LogedIn() {
        loginPage.loginAdmin();
        viewAllUsersPage.adminViewAllUsers();
        viewAllUsersPage.adminExploreUserProfile();
        profilePage.adminEditUserPersonalProfile();
        Assertions.assertEquals(getConfigPropertyByKey("soacial.app.editPersonalProfile.page"), getWebDriver().getCurrentUrl() ,
                "Page not successfully navigated");
    }

   @Test
   public void adminSuccessfullyUpdateUserProfessionalProfile_when_LoggedIn(){
       loginPage.loginAdmin();
       viewAllUsersPage.adminViewAllUsers();
       viewAllUsersPage.adminExploreUserProfile();
        profilePage.adminEditUserProfessionalProfile();
        actions.assertElementPresent("profile.page.assertProfessionChanged.element");
    }
    @Test
    public void adminSuccessfullyUpdateSkillsAndAvailability_when_LoggedIn(){
        loginPage.loginAdmin();
        viewAllUsersPage.adminViewAllUsers();
        viewAllUsersPage.adminExploreUserProfile();
        profilePage.EditSkills();
        actions.assertElementPresent("profilePage.editProfile.button");
    }
}
