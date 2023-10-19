package pages.weare;

import org.openqa.selenium.WebDriver;

public class ViewAllUsersPage extends WEareBasePage {

    public ViewAllUsersPage(WebDriver driver) {
        super(driver, "social.app.viewAllUsersPage");
    }


    ViewAllUsersPage viewAllUsersPage;

    public void adminViewAllUsers() {

        navigateToPage();
        assertPageNavigated();
    }

    public void adminExploreUserProfile() {


        actions.waitForElementClickable("viewAllUsersPage.viewFirstProfile.button");
        actions.clickElement("viewAllUsersPage.viewFirstProfile.button");
    }






}
