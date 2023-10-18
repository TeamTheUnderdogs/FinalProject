package pages.weare;

import Models.Admin;
import Models.User;
import com.weare.testframework.UserActions;
import com.weare.testframework.Utils;
import com.weare.testframework.pages.BasePage;
import jdk.jshell.execution.Util;
import org.openqa.selenium.WebDriver;

import static factories.AdminFactory.createAdmin;
import static factories.UserFactory.createUser;

public class WEareBasePage extends BasePage {

User user = createUser();
Admin  admin = createAdmin();

    public WEareBasePage(WebDriver driver, String pageUrlKey) {
        super(driver, pageUrlKey);
    }


}

