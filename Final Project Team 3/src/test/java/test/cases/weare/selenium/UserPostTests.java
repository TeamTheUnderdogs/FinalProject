package test.cases.weare.selenium;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.weare.LoginPage;
import pages.weare.PostPage;
import pages.weare.PostsPage;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static com.weare.testframework.Utils.getWebDriver;

public class UserPostTests extends BaseTest {
    PostsPage postsPage = new PostsPage(actions.getDriver());
    LoginPage loginPage = new LoginPage(actions.getDriver());
    PostPage postPage = new PostPage(actions.getDriver());

    @Test
    public void browsePublicPostsByCategory_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.browsePublicPosts();
        postsPage.registered_browsePublicPostsByCategory();
        Assertions.assertEquals(getConfigPropertyByKey("social.app.postsByCategoryPage"),
                getWebDriver().getCurrentUrl(), "Page not successfully navigated");
        actions.assertElementPresent("postsPage.assertSearchByCategory.element");
    }
    @Test
    public void userExplorePublicPost_when_LoggedIn() {
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");
        postPage.registered_explorePublicPost();
        actions.assertElementPresent("explorePostPage.explorePost.sign");
    }
    @Test
    public void userExploreProfileOfThePostAuthor_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.browsePublicPosts();
        postsPage.registered_browseAllPublicPosts();
        postPage.registered_explorePublicPost();
        postPage.exploreProfileOfThePostAuthor();
        actions.assertElementPresent("profilePage.personalInformation.menu");
    }
    @Test
    public void userExploreAllPostsFromSameAuthor_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.browsePublicPosts();
        postsPage.registered_browseAllPublicPosts();
        postPage.registered_explorePublicPost();
        postsPage.registered_exploreAllPostsFromSameAuthor();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }
    @Test
    public void userLikePublicPost_when_LoggedIn() {
        loginPage.loginUser();
        postPage.registered_createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postPage.registered_likePublicPost();
        actions.assertElementPresent("postsPage.dislikePost.button");
    }
    @Test
    public void userDislikePublicPost_when_LoggedIn() {
        loginPage.loginUser();
        postPage.registered_createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postPage.registered_likePublicPost();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postPage.registered_dislikePublicPost();
        actions.assertElementPresent("postsPage.likePost.button");
    }
    @Test
    public void userCreatePostWithOnlyText_when_LoggedIn() {
        loginPage.loginUser();
        postPage.registered_createPostWithOnlyText();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }
    @Test
    public void userCreatePublicPostWithTextAndPicture_when_LoggedIn() {
        loginPage.loginUser();
        postPage.registered_createPublicPostWithTextAndPicture();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }
    @Test
    public void userCreatePostWithThousandCharacters_when_LoggedIn() {
        loginPage.loginUser();
        postPage.registered_createPostWithThousandCharacters();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }
    @Test
    public void userEditOwnPost_when_LoggedIn() {
        loginPage.loginUser();
        postPage.registered_editOwnPost();
        this.actions.assertElementPresent("postPage.exploreAllPosts.header");
    }
    @Test
    public void userLikeOwnPost_when_LoggedIn() {
        loginPage.loginUser();
        postPage.registered_createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postPage.registered_likeOwnPost();
        this.actions.waitForElementVisible("postsPage.dislikePost.button");
        this.actions.assertElementPresent("postsPage.dislikePost.button");
    }
    @Test
    public void userDislikeOwnPost_when_LoggedIn() {
        loginPage.loginUser();
        postPage.registered_createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postPage.registered_dislikeOwnPost();
        this.actions.waitForElementVisible("postsPage.likePost.button");
        this.actions.assertElementPresent("postsPage.likePost.button");
    }
    @Test
    public void userCommentOwnPost_when_LoggedIn() {
        loginPage.loginUser();
        postPage.registered_commentOwnPost();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }
    @Test
    public void userDeleteOwnPost_when_LoggedIn() {
        loginPage.loginUser();
        postPage.registered_deleteOwnPost();
        actions.waitForElementVisible("postPage.deletePostVerification.item");
    }
}

