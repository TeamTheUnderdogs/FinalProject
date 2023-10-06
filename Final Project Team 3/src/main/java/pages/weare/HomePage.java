package pages.weare;

import org.openqa.selenium.WebDriver;

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
}
