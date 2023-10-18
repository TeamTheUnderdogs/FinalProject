package pages.weare;

import com.weare.testframework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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


        actions.waitForElementClickable("profilePage.editProfile.button");
        actions.clickElement("profilePage.editProfile.button");

        actions.waitForElementVisible("profilePage.addSkill.field");
        actions.clearValueInField("profilePage.addSkill.field");
        actions.typeValueInField(user.getIntroduction(),"profilePage.addSkill.field");

        actions.waitForElementVisible("profilePage.weeklyAvailability.field");
        actions.clearValueInField("profilePage.weeklyAvailability.field");
        actions.typeValueInField("4","profilePage.weeklyAvailability.field");

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
}



