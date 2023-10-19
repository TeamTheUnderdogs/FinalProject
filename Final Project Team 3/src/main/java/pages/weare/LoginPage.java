package pages.weare;

import com.weare.testframework.UserActions;
import org.openqa.selenium.WebDriver;

import static com.weare.testframework.Utils.getConfigPropertyByKey;

public class LoginPage extends WEareBasePage {

 public LoginPage(WebDriver driver) {
        super(driver, "social.app.loginPage");
    }
    public void loginUser() {
        String username = getConfigPropertyByKey("social.app.users.user.username");
        String password = getConfigPropertyByKey("social.app.users.user.password");

        navigateToPage();
        assertPageNavigated();

        actions.waitForElementVisible("loginPage.username.field");
        actions.typeValueInField(username,"loginPage.username.field");

        actions.waitForElementVisible("loginPage.password.field");
        actions.typeValueInField(password,"loginPage.password.field");

        actions.waitForElementClickable("loginPage.login.button");
        actions.clickElement("loginPage.login.button");}

    public void loginUserTwo() {
        String username = getConfigPropertyByKey("social.app.users.userTwo.username");
        String password = getConfigPropertyByKey("social.app.users.userTwo.password");

        navigateToPage();
        assertPageNavigated();

        actions.waitForElementVisible("loginPage.username.field");
        actions.typeValueInField(username,"loginPage.username.field");

        actions.waitForElementVisible("loginPage.password.field");
        actions.typeValueInField(password,"loginPage.password.field");

        actions.waitForElementClickable("loginPage.login.button");
        actions.clickElement("loginPage.login.button");}


    public void loginAdmin() {
        String username = getConfigPropertyByKey("social.app.users.admin.username");
        String password = getConfigPropertyByKey("social.app.users.admin.password");

        navigateToPage();
        assertPageNavigated();

        actions.waitForElementVisible("loginPage.username.field");
        actions.typeValueInField(username,"loginPage.username.field");

        actions.waitForElementVisible("loginPage.password.field");
        actions.typeValueInField(password,"loginPage.password.field");

        actions.waitForElementClickable("loginPage.login.button");
        actions.clickElement("loginPage.login.button");}

      public void logoutUser() {
       loginUser();

          actions.waitForElementClickable("homePage.logout.button");
          actions.clickElement("homePage.logout.button");}

    public void logoutAdmin() {
        loginAdmin();

        actions.waitForElementClickable("homePage.logout.button");
        actions.clickElement("homePage.logout.button");}

    public void clickLogoutButton() {
        actions.waitForElementClickable("homePage.logout.button");
        actions.clickElement("homePage.logout.button");
    }


}


