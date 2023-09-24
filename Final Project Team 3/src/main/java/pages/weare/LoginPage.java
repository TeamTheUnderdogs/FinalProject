package pages.weare;

import org.openqa.selenium.WebDriver;

import static com.weare.testframework.Utils.getConfigPropertyByKey;

public class LoginPage extends BaseLandingPage {

    public LoginPage(WebDriver driver) {
        super(driver, "social.app.loginPage");
    }

    public void loginUser() {
        String username = getConfigPropertyByKey("social.app.users.user.username");
        String password = getConfigPropertyByKey("social.app.user.password");

        // Go to login page
        navigateToPage();
        assertPageNavigated();

        // TODO: sign in
    }

    public void logoutUser() {
        // TODO: sign out
    }
}
