package pages.weare;

import Models.Admin;
import Models.User;
import org.openqa.selenium.WebDriver;

import static factories.AdminFactory.createAdmin;
import static factories.UserFactory.createUser;

public class ProfilePage extends WEareBasePage {
    public ProfilePage(WebDriver driver) {
        super(driver, "social.app.profile.page");
    }

    User user = createUser();
    Admin admin = createAdmin();
    ViewAllUsersPage viewAllUsersPage = new ViewAllUsersPage(actions.getDriver());

    public void adminEditUserPersonalProfile() {


        viewAllUsersPage.adminExploreUserProfile();

        actions.waitForElementClickable("profilePage.editProfile.button");
        actions.clickElement("profilePage.editProfile.button");

        actions.waitForElementVisible("profilePage.firstName.field");
        actions.clearValueInField("profilePage.firstName.field");
        actions.typeValueInField("Test Name", "profilePage.firstName.field");

        actions.waitForElementVisible("profilePage.lastName.field");
        actions.clearValueInField("profilePage.lastName.field");
        actions.typeValueInField(user.getLastName(), "profilePage.lastName.field");

        actions.selectFromDropdownMenu("profilePage.genderSelect.menu", "FEMALE");

        actions.waitForElementVisible("profilePage.email.field");
        actions.clearValueInField("profilePage.email.field");
        actions.typeValueInField(user.getEmail(), "profilePage.email.field");

        actions.waitForElementVisible("profilePage.introduction.field");
        actions.clearValueInField("profilePage.introduction.field");
        actions.typeValueInField(user.getIntroduction(), "profilePage.introduction.field");

        actions.selectFromDropdownMenu("profilePage.citySelect.menu", "Plovdiv");

        actions.waitForElementClickable("profilePage.updatePersonalProfile.button");
        actions.clickElement("profilePage.updatePersonalProfile.button");
    }

    public void adminEditUserProfessionalProfile() {


        viewAllUsersPage.adminExploreUserProfile();

        actions.waitForElementClickable("profilePage.editProfile.button");
        actions.clickElement("profilePage.editProfile.button");

        actions.waitForElementVisible("profilePage.professionSelect.menu");
        actions.selectFromDropdownMenu("profilePage.professionSelect.menu", "Baker");


        actions.waitForElementClickable("profilePage.updateProfession.button");
        actions.clickElement("profilePage.updateProfession.button");
    }

    public void EditSkills() {


        viewAllUsersPage.adminExploreUserProfile();

        actions.waitForElementClickable("profilePage.editProfile.button");
        actions.clickElement("profilePage.editProfile.button");

        actions.waitForElementVisible("profilePage.professionSelect.menu");
        actions.selectFromDropdownMenu("profilePage.professionSelect.menu", "Baker");


        actions.waitForElementClickable("profilePage.updateProfession.button");
        actions.clickElement("profilePage.updateProfession.button");
    }
}



