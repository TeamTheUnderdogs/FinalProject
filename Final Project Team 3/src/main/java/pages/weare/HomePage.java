package pages.weare;

import org.openqa.selenium.WebDriver;

public class HomePage extends BaseLandingPage {

    public HomePage(WebDriver driver) {
        super(driver, "social.app.homePage");
    }

    public void assertLoginButtonExists() {
        actions.waitForElementPresent("home.login.button");
    }

    public void assertRegisterButtonExists() {
        actions.waitForElementPresent("home.register.button");
    }

    public void assertProfileButtonExists() {
        actions.waitForElementPresent("home.profile.button");
    }
}
