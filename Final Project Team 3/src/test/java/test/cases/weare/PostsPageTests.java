package test.cases.weare;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        actions.assertElementPresent("postsPage.exploreAllPosts.sign");

    }

    @Test
    public void browsePublicPostsByCategory_when_Anonymous() {
        postsPage.anonymous_browsePublicPostsByCategory();
        Assertions.assertEquals(getConfigPropertyByKey("social.app.postsByCategoryPage"), getWebDriver().getCurrentUrl(),
                "Page not successfully navigated");

        actions.assertElementPresent("postsPage.assertSearchByCategory.element");


    }

    @Test
    public void browsePublicPostsByCategory_when_Logedin() {
        postsPage.registered_browsePublicPostsByCategory();
        Assertions.assertEquals(getConfigPropertyByKey("social.app.postsByCategoryPage"), getWebDriver().getCurrentUrl(),
                "Page not successfully navigated");

        actions.assertElementPresent("postsPage.assertSearchByCategory.element");


    }

    @Test

    public void explorePublicPost (){
        postsPage.explorePublicPost();
        actions.assertElementPresent("explorePostPage.explorePost.sign");
    }

    @Test
    public void userLikePublicPost() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.likePublicPost();
        actions.assertElementPresent("postsPage.dislikePost.button");
        actions.assertElementAttribute ("postsPage.dislikePost.button",
                "value", "Dislike");
        postsPage.dislikePublicPost();
    }

    @Test
    public void userDislikePublicPost() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.likePublicPost();
        actions.assertElementPresent("postsPage.likePost.button");
        actions.assertElementAttribute ("postsPage.likePost.button", "value", "Like");
    }

}

