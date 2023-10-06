package test.cases.weare;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import pages.weare.HomePage;

import com.weare.testframework.Utils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HomePageTests extends BaseTest {

    @Test
    public void hasLoginButtonForAnonymous() {
        // TODO: ensure logged out
        HomePage homePage = new HomePage(actions.getDriver());
        homePage.assertLoginButtonExists();
    }

    @Test
    public void hasRegisterButtonForAnonymous() {
        // TODO: ensure logged out
        HomePage homePage = new HomePage(actions.getDriver());
        homePage.assertRegisterButtonExists();
    }

    @Test
    public void noProfileButtonForAnonymous() {
        // TODO: ensure logged out
        HomePage homePage = new HomePage(actions.getDriver());
        AssertionFailedError thrown = assertThrows(AssertionFailedError.class, homePage::assertProfileButtonExists);

        String message = String.format("Element with locator: '%s' was not found.",
                Utils.getUIMappingByKey("homePage.profile.button"));
        assertEquals(message, thrown.getMessage());
    }

    @Test
    public void hasProfileButtonForLoggedIn() {
        // TODO: ensure logged in
        HomePage homePage = new HomePage(actions.getDriver());
        homePage.assertProfileButtonExists();
    }
}
