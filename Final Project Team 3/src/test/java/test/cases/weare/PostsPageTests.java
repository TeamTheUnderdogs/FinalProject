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

    @Test
    public void readCommentInPost() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.readCommentsInPost();
        actions.assertElementPresent("postPage.showNumberOfLikes.element");
    }

    @Test
    public void exploreAllPostsFromSomeAuthor() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.exploreAllPostsFromSomeAuthor();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }

    @Test
    public void exploreProfileOfThePostAuthor() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.exploreProfileOfThePostAuthor();
        actions.assertElementPresent("profilePage.personalInformation.menu");
    }

    @Test
    public void likeComment() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.likeComment();
        actions.assertElementPresent("postPage.dislikeComment");
    }

    @Test
    public void dislikeComment() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.dislikeComment();
        actions.assertElementPresent("postPage.likeComment");
    }

    @Test
    public void createPostWithOnlyText() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.createPostWithOnlyText();
        actions.assertElementPresent("postPage.exploreAllPosts.header");

    }

    @Test
    public void createPostWithThousandCharacters() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.createPostWithThousandCharacters();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }

    @Test
    public void createCommentWithThousandCharacters() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.createCommentWithThousandCharacters();
        actions.assertElementPresent("explorePostPage.explorePost.sign");
    }

    @Test
    public void commentOwnPost() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.commentOwnPost();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }
}

