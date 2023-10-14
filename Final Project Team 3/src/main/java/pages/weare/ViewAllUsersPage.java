package pages.weare;

import org.openqa.selenium.WebDriver;

public class ViewAllUsersPage extends WEareBasePage{

    public ViewAllUsersPage (WebDriver driver) {
        super(driver, "social.app.viewAllUsersPage");
    }

    LoginPage loginPage = new LoginPage(actions.getDriver());

    public void adminViewAllUsers(){

        loginPage.loginAdmin();
        ViewAllUsersPage viewAllUsersPage = new ViewAllUsersPage(actions.getDriver());
navigateToPage();
    }

    public void adminExploreUserProfile(){
        loginPage.loginAdmin();
        ViewAllUsersPage viewAllUsersPage = new ViewAllUsersPage(actions.getDriver());
        navigateToPage();
        assertPageNavigated();
        actions.waitForElementClickable("viewAllUsersPage.viewFirstProfile.button");
        actions.clickElement("viewAllUsersPage.viewFirstProfile.button");
    }


}
