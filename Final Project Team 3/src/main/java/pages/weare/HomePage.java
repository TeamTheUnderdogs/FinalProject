package pages.weare;

import org.openqa.selenium.WebDriver;

public class HomePage extends WEareBasePage {


    public HomePage(WebDriver driver) {
        super(driver, "social.app.homePage");
    }


    public void searchUserByCategory() {

        actions.waitForElementVisible("homePage.searchUsersByCategory.field");
        actions.typeValueInField("baker", "homePage.searchUsersByCategory.field");

        actions.waitForElementClickable("homePage.searchUsers.button");
        actions.clickElement("homePage.searchUsers.button");

    }

    public void searchUserByName(String name) {


        navigateToPage();
        assertPageNavigated();

        actions.waitForElementVisible("homePage.searchUsersByName.field");
        actions.typeValueInField(name, "homePage.searchUsersByName.field");

        actions.waitForElementClickable("homePage.searchUsers.button");
        actions.clickElement("homePage.searchUsers.button");

    }

    public void userOpenOwnProfile() {
        actions.waitForElementClickable("homePage.personalProfile.button");
        actions.clickElement("homePage.personalProfile.button");
    }

    public void clickHomeButton() {

        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
    }
}
