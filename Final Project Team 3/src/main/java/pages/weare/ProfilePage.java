package pages.weare;

import com.weare.testframework.Utils;
import org.openqa.selenium.WebDriver;

import static factories.UserFactory.faker;

public class ProfilePage extends WEareBasePage {
    public ProfilePage(WebDriver driver) {
        super(driver, "social.app.profile.page");
    }


    public void adminEditUserPersonalProfile() {


        actions.waitForElementClickable("profilePage.editProfile.button");
        actions.clickElement("profilePage.editProfile.button");

        actions.waitForElementVisible("profilePage.firstName.field");
        actions.clearValueInField("profilePage.firstName.field");
        actions.typeValueInField("Test Name", "profilePage.firstName.field");

        actions.waitForElementVisible("profilePage.lastName.field");
        actions.clearValueInField("profilePage.lastName.field");
        actions.typeValueInField(user.getLastName(), "profilePage.lastName.field");

        actions.waitForElementVisible("profilePage.birthday.field");
        actions.clearValueInField("profilePage.birthday.field");
        String birthdayDate = Utils.formatBirthdayDate(faker.date().birthday());
        actions.typeValueInField(birthdayDate, "profilePage.birthday.field");

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




        actions.waitForElementClickable("profilePage.editProfile.button");
        actions.clickElement("profilePage.editProfile.button");

        actions.waitForElementVisible("profilePage.professionSelect.menu");
        actions.selectFromDropdownMenu("profilePage.professionSelect.menu", "Baker");


        actions.waitForElementClickable("profilePage.updateProfession.button");
        actions.clickElement("profilePage.updateProfession.button");
    }

    public void EditSkills() {


       ViewAllUsersPage viewAllUsersPage= new ViewAllUsersPage(actions.getDriver());
       viewAllUsersPage.adminExploreUserProfile();

        actions.waitForElementClickable("profilePage.editProfile.button");
        actions.clickElement("profilePage.editProfile.button");

        actions.waitForElementVisible("profilePage.professionSelect.menu");
        actions.selectFromDropdownMenu("profilePage.professionSelect.menu", "Baker");


        actions.waitForElementClickable("profilePage.updateProfession.button");
        actions.clickElement("profilePage.updateProfession.button");
    }
}



