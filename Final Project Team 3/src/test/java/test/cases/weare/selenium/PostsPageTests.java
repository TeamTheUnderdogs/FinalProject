package test.cases.weare.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pages.weare.LoginPage;
import pages.weare.PostsPage;

import static com.weare.testframework.Utils.*;


public class PostsPageTests extends BaseTest {

    PostsPage postsPage = new PostsPage(actions.getDriver());
    LoginPage loginPage = new LoginPage(actions.getDriver());


    @Test
    public void browsePublicPosts_when_Anonymous() {

        postsPage.browsePublicPosts();

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

    public void explorePublicPost_when_Logged (){
        postsPage.registered_explorePublicPost();
        actions.assertElementPresent("explorePostPage.explorePost.sign");
    }

    @Test
    public void userLikePublicPost() {

        loginPage.loginUser();

        postsPage.registered_likePublicPost();
        actions.assertElementPresent("postsPage.dislikePost.button");
        actions.assertElementAttribute ("postsPage.dislikePost.button",
                "value", "Dislike");

    }

    @Test
    public void userDislikePublicPost() {
       loginPage.loginUser();

        postsPage.registered_dislikePublicPost();
        actions.assertElementPresent("postsPage.likePost.button");
        actions.assertElementAttribute ("postsPage.likePost.button", "value", "Like");
    }

    @Test
    public void readCommentInPost() {
        loginPage.loginUser();
        postsPage.browsePublicPosts();
        postsPage.browseAllPublicPosts_registered();
        postsPage.registered_explorePublicPost();
        postsPage.readCommentsInPost();
        actions.assertElementPresent("postPage.showNumberOfLikesForComment.element");
    }

    @Test
    public void exploreAllPostsFromSameAuthor() {
        loginPage.loginUser();
        postsPage.browsePublicPosts();
        postsPage.browseAllPublicPosts_registered();
        postsPage.registered_explorePublicPost();
        postsPage.exploreAllPostsFromSameAuthor();
        actions.assertElementPresent("postPage.exploreAllPosts.header");


    }

    @Test
    public void exploreProfileOfThePostAuthor() {
        loginPage.loginUser();
        postsPage.browsePublicPosts();
        postsPage.browseAllPublicPosts_registered();

        postsPage.registered_explorePublicPost();

        postsPage.exploreProfileOfThePostAuthor();
        actions.assertElementPresent("profilePage.personalInformation.menu");
    }

    @Test
    public void likeComment() {
        loginPage.loginUser();
        postsPage.browsePublicPosts();
        postsPage.browseAllPublicPosts_registered();

        postsPage.registered_explorePublicPost();
        postsPage.readCommentsInPost();
        postsPage.likeComment();
        actions.waitForElementClickable("postPage.dislikeComment.button");
        actions.assertElementPresent("postPage.dislikeComment.button");
        postsPage.dislikeComment();
    }

    @Test
    public void dislikeComment() {
        loginPage.loginUser();
        postsPage.browsePublicPosts();
        postsPage.browseAllPublicPosts_registered();

        postsPage.registered_explorePublicPost();
        postsPage.readCommentsInPost();
        postsPage.dislikeComment();
        actions.assertElementPresent("postPage.likeComment.button");
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
    public void editOwnPost() {
        PostsPage postsPage = new PostsPage(this.actions.getDriver());
        postsPage.editOwnPost();
        this.actions.assertElementPresent("postPage.exploreAllPosts.header");
    }

    @Test
    public void likeOwnPost() {
        PostsPage postsPage = new PostsPage(this.actions.getDriver());
        postsPage.likeOwnPost();
        this.actions.waitForElementVisible("postsPage.dislikePost.button", new Object[0]);
        this.actions.assertElementPresent("postsPage.dislikePost.button");
    }

    @Test
    public void dislikeOwnPost() {
        PostsPage postsPage = new PostsPage(this.actions.getDriver());
        postsPage.dislikeOwnPost();
        this.actions.waitForElementVisible("postsPage.likePost.button", new Object[0]);
        this.actions.assertElementPresent("postsPage.likePost.button");
    }

    @Test
    public void commentOwnPost() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.commentOwnPost();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }

    @Test
    public void deleteOwnPost() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.deleteOwnPost();
        actions.waitForElementVisible("postPage.deletePostVerification.item");
    }

    @Test
    public void editOwnComment() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.editOwnComment();
    }

    @Test
    public void deleteOwnComment() {
        PostsPage postsPage = new PostsPage(actions.getDriver());
        postsPage.deleteOwnComment();
        actions.assertElementPresent("postPage.deleteComment.item");

    }

    @Test
    public void createPublicPostWithTextAndPicture() {
       loginPage.loginUser();
        postsPage.createPublicPostWithTextAndPicture();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }
}

