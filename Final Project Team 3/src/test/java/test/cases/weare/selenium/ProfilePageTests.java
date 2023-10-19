package test.cases.weare.selenium;

import org.junit.jupiter.api.Test;
import pages.weare.LoginPage;
import pages.weare.ProfilePage;
import pages.weare.ViewAllUsersPage;

public class ProfilePageTests extends BaseTest {

    LoginPage loginPage = new LoginPage(actions.getDriver());
    ViewAllUsersPage viewAllUsersPage = new ViewAllUsersPage(actions.getDriver());
    ProfilePage profilePage = new ProfilePage(actions.getDriver());


@Test

    public void adminSuccessfullyUpdateUserPersonalProfile_when_LogedIn() {
        loginPage.loginAdmin();
        viewAllUsersPage.adminViewAllUsers();
        viewAllUsersPage.adminExploreUserProfile();
        profilePage.EditUserPersonalProfile();

        actions.assertElementPresent("profilePage.UpdateProfessionalProfile.menuTab");
    }

   @Test
   public void adminSuccessfullyUpdateUserProfessionalProfile_when_LoggedIn(){
       loginPage.loginAdmin();
       viewAllUsersPage.adminViewAllUsers();
       viewAllUsersPage.adminExploreUserProfile();
        profilePage.EditUserProfessionalProfile();
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

    @Test
    public void adminSuccessfullyUpdateProfilePicture_whenLoggedIn() {
        loginPage.loginAdmin();
        viewAllUsersPage.adminViewAllUsers();
        viewAllUsersPage.adminExploreUserProfile();
        profilePage.AddProfilePicture();
        actions.assertElementPresent("profilePage.editProfile.button");
    }

    @Test

    public void adminSuccessfullyDisableUserProfile () {
        loginPage.loginAdmin();
        viewAllUsersPage.navigateToPage();
        viewAllUsersPage.adminExploreUserProfile();
        profilePage.adminDisableProfile();
        actions.assertElementPresent("userPage.enableProfile.button");
        profilePage.adminEnableProfile();

    }
@Test
    public void adminSuccessfullyEnableUserProfile () {
        loginPage.loginAdmin();
        viewAllUsersPage.navigateToPage();
        viewAllUsersPage.adminExploreUserProfile();
        profilePage.adminDisableProfile();
        profilePage.adminEnableProfile();

        actions.assertElementPresent("userPage.disableProfile.button");

    }
    @Test
    public void UserSuccessfullyUpdatePersonalProfile_when_LogedIn() {
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");

        profilePage.EditUserPersonalProfile();
        actions.assertElementPresent("profilePage.UpdateProfessionalProfile.menuTab");
    }
    @Test
    public void UserSuccessfullyUpdateProfessionalProfile_when_LoggedIn(){
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");
        profilePage.EditUserProfessionalProfile();
        actions.assertElementPresent("profile.page.assertProfessionChanged.element");
    }
    @Test
    public void UserSuccessfullyUpdateSkillsAndAvailability_when_LoggedIn(){
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");
        profilePage.EditSkills();

        actions.assertElementPresent("profilePage.editProfile.button");
    }
    @Test
    public void UserSuccessfullyUpdateProfilePicture_whenLoggedIn() {
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");

        profilePage.AddProfilePicture();
        actions.assertElementPresent("profilePage.editProfile.button");
    }
}
