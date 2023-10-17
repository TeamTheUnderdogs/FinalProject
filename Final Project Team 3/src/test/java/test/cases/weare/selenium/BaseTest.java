package test.cases.weare.selenium;

import Models.Admin;
import Models.User;
import com.weare.testframework.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.weare.*;

import com.weare.testframework.UserActions;

public class BaseTest {

    UserActions actions = new UserActions();
    User user;
    Admin admin;
    HomePage homepage;
    LoginPage loginPage;
    PostsPage postsPage;
    PostPage postPage;
    ProfilePage profilePage;
    RegistrationPage registrationPage;
    ViewAllUsersPage viewAllUsersPage;


    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser("social.app.homePage");
    }

    @AfterAll
    public static void tearDown() {
        UserActions.quitDriver();
    }


}