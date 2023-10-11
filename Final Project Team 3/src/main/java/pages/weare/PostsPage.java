package pages.weare;

import org.openqa.selenium.WebDriver;

public class PostsPage extends WEareBasePage {
    public PostsPage(WebDriver driver) {
        super(driver, "social.app.postsPage");
    }

    public void browsePublicPosts() {
       navigateToPage();
       assertPageNavigated();


    }
}
