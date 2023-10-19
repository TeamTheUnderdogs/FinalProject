package test.cases.weare.selenium;

import Models.Admin;
import Models.User;
import com.weare.testframework.UserActions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pages.weare.*;

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