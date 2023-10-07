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


    public void registerUser () {

        User user = createUser();

        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();


        actions.waitForElementVisible("homePage.register.button");
        actions.clickElement("homePage.register.button");

        actions.waitForElementVisible("registrationPage.username.field");
        actions.typeValueInField(username,"registrationPage.username.field");

        actions.waitForElementVisible("registrationPage.email.field");
        actions.typeValueInField(email,"registrationPage.email.field");

        actions.waitForElementVisible("registrationPage.password.field");
        actions.typeValueInField(password,"registrationPage.password.field");

        actions.waitForElementVisible("registrationPage.confirmPassword.field");
        actions.typeValueInField(password,"registrationPage.confirmPassword.field");


        actions.clickElement("registrationPage.register.button");
    }

    public void registerAdmin () {

        Admin admin = createAdmin();

        String username = admin.getUsername();
        String email = admin.getEmail();
        String password = admin.getPassword();


        actions.waitForElementVisible("homePage.register.button");
        actions.clickElement("homePage.register.button");

        actions.waitForElementVisible("registrationPage.username.field");
        actions.typeValueInField(username,"registrationPage.username.field");

        actions.waitForElementVisible("registrationPage.email.field");
        actions.typeValueInField(email,"registrationPage.email.field");

        actions.waitForElementVisible("registrationPage.password.field");
        actions.typeValueInField(password,"registrationPage.password.field");

        actions.waitForElementVisible("registrationPage.confirmPassword.field");
        actions.typeValueInField(password,"registrationPage.confirmPassword.field");

        actions.waitForElementClickable("registrationPage.register.button");
        actions.clickElement("registrationPage.register.button");

        actions.waitForElementClickable("homePage.login.button");
        actions.clickElement("homePage.login.button");

        actions.waitForElementVisible("loginPage.username.field");
        actions.typeValueInField(username,"loginPage.username.field");

        actions.waitForElementVisible("loginPage.password.field");
        actions.typeValueInField(password,"loginPage.password.field");

        actions.waitForElementClickable("loginPage.login.button");
        actions.clickElement("loginPage.login.button");

        actions.waitForElementClickable("home.Page.adminZone.button");
        actions.clickElement("home.Page.adminZone.button");

        actions.waitForElementClickable("adminPage.viewUsers.button");
        actions.clickElement("adminPage.viewUsers.button");

        actions.waitForElementClickable("viewUsersPage.viewFirstProfile.button");
        actions.clickElement("viewUsersPage.viewFirstProfile.button");


    }


}
