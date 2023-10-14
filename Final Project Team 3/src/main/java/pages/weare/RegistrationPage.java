package pages.weare;

import Models.Admin;
import Models.User;
import org.openqa.selenium.WebDriver;

import static factories.AdminFactory.createAdmin;
import static factories.UserFactory.createUser;

public class RegistrationPage extends WEareBasePage {

    public RegistrationPage (WebDriver driver) {
        super(driver, "social.app.registrationPage");
    }

    User user = createUser();
    Admin admin = createAdmin();


    public void registerUser () {

        actions.waitForElementClickable("homePage.register.button");
        actions.clickElement("homePage.register.button");

        actions.waitForElementVisible("registrationPage.username.field");
        actions.typeValueInField(user.getUsername(),"registrationPage.username.field");

        actions.waitForElementVisible("registrationPage.email.field");
        actions.typeValueInField(user.getEmail(), "registrationPage.email.field");

        actions.waitForElementVisible("registrationPage.password.field");
        actions.typeValueInField(user.getPassword(),"registrationPage.password.field");

        actions.waitForElementVisible("registrationPage.confirmPassword.field");
        actions.typeValueInField(user.getPassword(),"registrationPage.confirmPassword.field");


        actions.clickElement("registrationPage.register.button");
    }

    public void registerAdmin () {

        actions.waitForElementVisible("homePage.register.button");
        actions.clickElement("homePage.register.button");

        actions.waitForElementVisible("registrationPage.username.field");
        actions.typeValueInField(admin.getUsername(),"registrationPage.username.field");

        actions.waitForElementVisible("registrationPage.email.field");
        actions.typeValueInField(admin.getEmail(),"registrationPage.email.field");

        actions.waitForElementVisible("registrationPage.password.field");
        actions.typeValueInField(admin.getPassword(),"registrationPage.password.field");

        actions.waitForElementVisible("registrationPage.confirmPassword.field");
        actions.typeValueInField(admin.getPassword(),"registrationPage.confirmPassword.field");

        actions.waitForElementClickable("registrationPage.register.button");
        actions.clickElement("registrationPage.register.button");

        actions.waitForElementClickable("homePage.login.button");
        actions.clickElement("homePage.login.button");

        actions.waitForElementVisible("loginPage.username.field");
        actions.typeValueInField(admin.getUsername(),"loginPage.username.field");

        actions.waitForElementVisible("loginPage.password.field");
        actions.typeValueInField(admin.getPassword(),"loginPage.password.field");

        actions.waitForElementClickable("loginPage.login.button");
        actions.clickElement("loginPage.login.button");

        actions.waitForElementClickable("home.Page.adminZone.button");
        actions.clickElement("home.Page.adminZone.button");

        actions.waitForElementClickable("adminPage.viewUsers.button");
        actions.clickElement("adminPage.viewUsers.button");

        actions.waitForElementClickable("viewAllUsersPage.viewFirstProfile.button");
        actions.clickElement("viewAllUsersPage.viewFirstProfile.button");


    }


}
