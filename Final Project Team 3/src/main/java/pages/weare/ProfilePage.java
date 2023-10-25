package pages.weare;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class ProfilePage extends WEareBasePage {
    public ProfilePage(WebDriver driver) {
        super(driver, "social.app.profile.page");
    }


    public void EditUserPersonalProfile() {

        actions.waitForElementClickable("profilePage.editProfile.button");
        actions.clickElement("profilePage.editProfile.button");

        actions.waitForElementVisible("profilePage.firstName.field");
        actions.clearValueInField("profilePage.firstName.field");
        actions.typeValueInField(user.getFirstName(), "profilePage.firstName.field");

        actions.waitForElementVisible("profilePage.lastName.field");
        actions.clearValueInField("profilePage.lastName.field");
        actions.typeValueInField(user.getLastName(), "profilePage.lastName.field");

        actions.waitForElementVisible("profilePage.birthday.field");
        WebElement dateInput = driver.findElement(By.xpath("//input[@id='birthDayE']"));
        dateInput.sendKeys("2002", Keys.TAB, "11", "11");

        actions.selectFromDropdownMenu("profilePage.genderSelect.menu", "FEMALE");

        actions.waitForElementVisible("profilePage.email.field");
        actions.clearValueInField("profilePage.email.field");
        actions.typeValueInField(user.getEmail(), "profilePage.email.field");

        actions.waitForElementVisible("profilePage.introduction.field");
        actions.clearValueInField("profilePage.introduction.field");
        actions.typeValueInField(user.getIntroduction(), "profilePage.introduction.field");

        actions.selectFromDropdownMenu("profilePage.citySelect.menu", "Sofia");

        actions.waitForElementClickable("profilePage.updatePersonalProfile.button");
        actions.clickElement("profilePage.updatePersonalProfile.button");
    }

    public void EditUserProfessionalProfile() {

        actions.waitForElementClickable("profilePage.editProfile.button");
        actions.clickElement("profilePage.editProfile.button");

        actions.waitForElementVisible("profilePage.professionSelect.menu");
        actions.selectFromDropdownMenu("profilePage.professionSelect.menu", "Baker");


        actions.waitForElementClickable("profilePage.updateProfession.button");
        actions.clickElement("profilePage.updateProfession.button");
    }

    public void EditSkills() {

        actions.waitForElementClickable("profilePage.editProfile.button");
        actions.clickElement("profilePage.editProfile.button");

        actions.waitForElementVisible("profilePage.addSkill.field");
        actions.clearValueInField("profilePage.addSkill.field");
        actions.typeValueInField(user.getSkill(), "profilePage.addSkill.field");

        actions.waitForElementVisible("profilePage.weeklyAvailability.field");
        actions.clearValueInField("profilePage.weeklyAvailability.field");
        actions.typeValueInField("4", "profilePage.weeklyAvailability.field");

        actions.waitForElementClickable("profilePage.updateSkillsAndAvailability.button");
        actions.clickElement("profilePage.updateSkillsAndAvailability.button");
    }

    public void AddProfilePicture() {

        actions.waitForElementClickable("profilePage.editProfile.button");
        actions.clickElement("profilePage.editProfile.button");

        actions.waitForElementClickable("profilePage.picturePrivacy.menu");
        actions.selectFromDropdownMenu("profilePage.picturePrivacy.menu", "public");


        By fileInputSelector = By.xpath("//input[@type='file']");
        WebElement fileInput = driver.findElement(fileInputSelector);
        String filePath = new File("src/main/java/resources/testUser.jpg").getAbsolutePath();
        fileInput.sendKeys(filePath);
        actions.waitForElementClickable("profilePage.updatePrivacy.button");
        actions.clickElement("profilePage.updatePrivacy.button");


    }

    public void adminDisableProfile() {
        actions.waitForElementClickable("userPage.disableProfile.button");
        actions.clickElement("userPage.disableProfile.button");
    }

    public void adminEnableProfile() {
        actions.waitForElementClickable("userPage.enableProfile.button");
        actions.clickElement("userPage.enableProfile.button");
    }

    public void sendConnectionRequest() {
        actions.waitForElementClickable("searchPage.seeFirstProfile");
        actions.clickElement("searchPage.seeFirstProfile");

        actions.waitForElementClickable("profilePage.connect.button");
        actions.clickElement("profilePage.connect.button");

    }

    public void acceptConnectionRequest() {
        actions.waitForElementClickable("profilePage.newRequestsButton");
        actions.clickElement("profilePage.newRequestsButton");

        actions.waitForElementClickable("profilePage.approveRequest");
        actions.clickElement("profilePage.approveRequest");

    }

    public void disconnectFromUser() {
        actions.waitForElementClickable("searchPage.seeFirstProfile");
        actions.clickElement("searchPage.seeFirstProfile");

        actions.waitForElementClickable("profilePage.disconnect.button");
        actions.clickElement("profilePage.disconnect.button");

        actions.assertElementPresent("profilePage.connect.button");

    }
}



