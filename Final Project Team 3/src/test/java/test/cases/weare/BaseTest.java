package test.cases.weare;

import com.weare.testframework.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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