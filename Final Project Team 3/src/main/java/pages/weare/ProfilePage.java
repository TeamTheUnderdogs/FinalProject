package pages.weare;

import Models.Admin;
import Models.User;
import factories.UserFactory;
import org.openqa.selenium.WebDriver;

import static factories.AdminFactory.createAdmin;
import static factories.UserFactory.createUser;

public class ProfilePage extends WEareBasePage{
    public ProfilePage (WebDriver driver) {
        super(driver, "social.app.profile.page");
    }

    User user = createUser();
    Admin admin = createAdmin();

    public void editUserPersonalProfile(){
ViewAllUsersPage viewAllUsersPage=new ViewAllUsersPage(actions.getDriver());
viewAllUsersPage.adminExploreUserProfile();

actions.waitForElementVisible("userPage.editProfile.button");
actions.clickElement("userPage.editProfile.button");

actions.waitForElementVisible("profilePage.firstName.field");
actions.typeValueInField(user.getFirstName(),"profilePage.firstName.field");

actions.waitForElementVisible("profilePage.lastName.field");
actions.typeValueInField(user.getLastName(),"profilePage.lastName.field");

actions.waitForElementVisible("profilePage.birthday.field");
actions.typeValueInField(user.getBirthday(), "profilePage.birthday.field");

actions.selectFromDropdownMenu("profilePage.genderSelect.menu", "FEMALE");

actions.waitForElementVisible("profilePage.email.field");
actions.typeValueInField(user.getEmail(),"profilePage.email.field");

actions.waitForElementVisible("profilePage.introduction.field");
actions.typeValueInField(user.getIntroduction(),"profilePage.introduction.field");

actions.selectFromDropdownMenu("profilePage.citySelect.menu", "Plovdiv");

actions.waitForElementClickable("profilePage.updatePersonalProfile.button");
actions.clickElement("profilePage.updatePersonalProfile.button");
    }
}



