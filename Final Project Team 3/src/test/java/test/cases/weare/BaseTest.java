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


}