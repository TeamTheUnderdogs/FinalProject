package test.cases.weare.selenium;
import org.junit.jupiter.api.Test;
import pages.weare.HomePage;
import pages.weare.LoginPage;
import pages.weare.ProfilePage;

public class ConnectionTests extends BaseTest {
    LoginPage loginPage = new LoginPage(actions.getDriver());
    HomePage homePage = new HomePage(actions.getDriver());
    ProfilePage profilePage = new ProfilePage(actions.getDriver());

    @Test
    public void successfullyCreateConnection_between_twoUsers() {
        loginPage.loginUser();
        homePage.searchUserByName("Darina");
        profilePage.sendConnectionRequest();
        actions.assertElementPresent("profilePage.sentConnectRequest.message");
        loginPage.clickLogoutButton();
        loginPage.loginUserTwo();
        homePage.userOpenOwnProfile();
        profilePage.acceptConnectionRequest();
        actions.assertElementPresent("profilePage.requestAcceptedConfirm.text");
        homePage.searchUserByName("Doychin");
        profilePage.disconnectFromUser();
    }
}


