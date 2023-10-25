package test.cases.weare.selenium;
import org.junit.jupiter.api.Test;
import pages.weare.LoginPage;
import pages.weare.ProfilePage;
public class UserProfileTests extends BaseTest {
    LoginPage loginPage = new LoginPage(actions.getDriver());
    ProfilePage profilePage = new ProfilePage(actions.getDriver());

    @Test
    public void UserSuccessfullyUpdatePersonalProfile_when_LoggedIn() {
        loginPage.loginUserThree();
        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");
        profilePage.EditUserPersonalProfile();
        actions.assertElementPresent("profilePage.UpdateProfessionalProfile.menuTab");
    }
    @Test
    public void UserSuccessfullyUpdateProfessionalProfile_when_LoggedIn() {
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");
        profilePage.EditUserProfessionalProfile();
        actions.assertElementPresent("profile.page.assertProfessionChanged.element");
    }
    @Test
    public void UserSuccessfullyUpdateSkillsAndAvailability_when_LoggedIn() {
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
