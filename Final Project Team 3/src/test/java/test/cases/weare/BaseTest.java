package test.cases.weare;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pages.weare.LoginPage;

import com.weare.testframework.UserActions;

public class BaseTest {

    UserActions actions = new UserActions();

    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser("social.app.homePage");
    }

    @AfterAll
    public static void tearDown() {
        UserActions.quitDriver();
    }

    public static void login() {
        LoginPage loginPage = new LoginPage(new UserActions().getDriver());
        loginPage.loginUser();
    }
}