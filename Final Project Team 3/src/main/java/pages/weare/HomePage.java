package pages.weare;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static com.weare.testframework.Utils.getWebDriver;

public class HomePage extends WEareBasePage {

    public HomePage(WebDriver driver) {
        super(driver, "social.app.homePage");
    }

    public void assertLoginButtonExists() {
        actions.waitForElementPresent("homePage.login.button");
    }

    public void assertRegisterButtonExists() {
        actions.waitForElementPresent("homePage.register.button");
    }

    public void assertProfileButtonExists() {
        actions.waitForElementPresent("homePage.profile.button");
    }

public void searchUserByCategory () {
    actions.waitForElementVisible("homePage.searchUsersByCategory.field");
    actions.typeValueInField("baker", "homePage.searchUsersByCategory.field");

    actions.waitForElementVisible("homePage.searchUsers.button");
    actions.clickElement("homePage.searchUsers.button");

}
}
