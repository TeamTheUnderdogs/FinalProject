package test.cases.weare.selenium;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.weare.LoginPage;
import pages.weare.PostPage;
import pages.weare.PostsPage;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static com.weare.testframework.Utils.getWebDriver;

public class AnonymousPostsTests extends BaseTest {
    PostsPage postsPage = new PostsPage(actions.getDriver());
    LoginPage loginPage = new LoginPage(actions.getDriver());
    PostPage postPage = new PostPage(actions.getDriver());

    @Test
    public void browsePublicPosts_when_Anonymous() {
        postsPage.browsePublicPosts();
        Assertions.assertEquals(getConfigPropertyByKey("social.app.postsPage"), getWebDriver().getCurrentUrl(), "Page not successfully navigated");
        actions.assertElementPresent("postsPage.exploreAllPosts.sign");
    }
    @Test
    public void browsePublicPostsByCategory_when_Anonymous() {
        postsPage.browsePublicPosts();
        postsPage.anonymous_browsePublicPostsByCategory();
        Assertions.assertEquals(getConfigPropertyByKey("social.app.postsByCategoryPage"), getWebDriver().getCurrentUrl(), "Page not successfully navigated");
        actions.assertElementPresent("postsPage.assertSearchByCategory.element");
    }
}
