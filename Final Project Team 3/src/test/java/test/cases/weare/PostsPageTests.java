package test.cases.weare;

import com.weare.testframework.UserActions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.weare.PostsPage;

import static com.weare.testframework.Utils.*;


public class PostsPageTests extends BaseTest {

    PostsPage postsPage = new PostsPage(actions.getDriver());


    @Test
    public void browsePublicPosts_when_Anonymous() {

        postsPage.browsePublicPosts();

        actions.assertElementPresent("postsPage.browsePostsByCategory.button");

        Assertions.assertEquals(getConfigPropertyByKey("social.app.postsPage"),
                getWebDriver().getCurrentUrl(), "Page not successfully navigated");
        actions.assertElementPresent("postsPage.post.element");

    }

    @Test
    public void browsePublicPostsByCategory_when_Anonymous() {
        postsPage.browsePublicPostsByCategory();
        Assertions.assertEquals(getConfigPropertyByKey("social.app.postsByCategoryPage"), getWebDriver().getCurrentUrl(),
                "Page not successfully navigated");

        actions.assertElementPresent("postsPage.assertSearchByCategory.element");


    }

    @Test

    public void explorePublicPost (){
        postsPage.explorePublicPost();
        actions.assertElementPresent("explorePostPage.explorePost.sign");
    }
}

