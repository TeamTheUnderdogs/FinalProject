package pages.weare;

import com.weare.testframework.pages.BasePage;
import models.Admin;
import models.User;
import org.openqa.selenium.WebDriver;

import static factories.AdminFactory.createAdmin;
import static factories.UserFactory.createUser;

public class WEareBasePage extends BasePage {

    User user = createUser();
    Admin admin = createAdmin();

    public WEareBasePage(WebDriver driver, String pageUrlKey) {
        super(driver, pageUrlKey);
    }


}

