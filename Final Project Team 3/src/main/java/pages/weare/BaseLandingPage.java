package pages.weare;

import com.weare.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public abstract class BaseLandingPage extends BasePage {

    public BaseLandingPage(WebDriver driver, String pageUrlKey) {
        super(driver, pageUrlKey);
    }
}
