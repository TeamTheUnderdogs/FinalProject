package test.cases.weare.selenium;

import com.weare.testframework.UserActions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

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