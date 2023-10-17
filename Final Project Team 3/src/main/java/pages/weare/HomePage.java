package pages.weare;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class HomePage extends WEareBasePage {


    public HomePage(WebDriver driver) {
        super(driver, "social.app.homePage");
    }


 public void searchUserByCategory () {

    actions.waitForElementVisible("homePage.searchUsersByCategory.field");
    actions.typeValueInField("baker", "homePage.searchUsersByCategory.field");

    actions.waitForElementClickable("homePage.searchUsers.button");
    actions.clickElement("homePage.searchUsers.button");

}
}
