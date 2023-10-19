package test.cases.weare.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.weare.LoginPage;
import pages.weare.PostsPage;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static com.weare.testframework.Utils.getWebDriver;


public class PostsTests extends BaseTest {

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
        postsPage.browsePublicPosts();
        postsPage.anonymous_browsePublicPostsByCategory();
        Assertions.assertEquals(getConfigPropertyByKey("social.app.postsByCategoryPage"), getWebDriver().getCurrentUrl(),
                "Page not successfully navigated");
        actions.assertElementPresent("postsPage.assertSearchByCategory.element");
    }

    @Test
    public void browsePublicPostsByCategory_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.browsePublicPosts();
        postsPage.registered_browsePublicPostsByCategory();
        Assertions.assertEquals(getConfigPropertyByKey("social.app.postsByCategoryPage"), getWebDriver().getCurrentUrl(),
                "Page not successfully navigated");

        actions.assertElementPresent("postsPage.assertSearchByCategory.element");


    }

    @Test

    public void explorePublicPost_when_LoggedIn() {
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");
        postsPage.registered_explorePublicPost();
        actions.assertElementPresent("explorePostPage.explorePost.sign");
    }

    @Test
    public void userLikePublicPost_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.registered_likePublicPost();
        actions.assertElementPresent("postsPage.dislikePost.button");
    }

    @Test
    public void userDislikePublicPost_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.registered_likePublicPost();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.registered_dislikePublicPost();
        actions.assertElementPresent("postsPage.likePost.button");
    }

    @Test
    public void AdminSuccessfullyEditUserPublicPost_when_LoggedIn() {
        loginPage.loginAdmin();
        postsPage.browseAllPublicPosts_registered();
        postsPage.registered_explorePublicPost();
        postsPage.adminEditPublicPost();
        actions.assertElementPresent("postPage.assertPostEdit.element");

    }

    @Test
    public void AdminSuccessfullyDeleteUserPublicPost_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.createPostWithOnlyText();
        loginPage.loginAdmin();
        postsPage.browseAllPublicPosts_registered();
        postsPage.registered_explorePublicPost();
        postsPage.adminDeletePublicPost();
        actions.assertElementPresent("postPage.deletePostVerification.item");


    }

    @Test
    public void userReadCommentInPost_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.browsePublicPosts();
        postsPage.browseAllPublicPosts_registered();
        postsPage.registered_explorePublicPost();
        postsPage.readCommentsInPost();
        actions.assertElementPresent("postPage.showNumberOfLikesForComment.element");
    }

    @Test
    public void userExploreAllPostsFromSameAuthor_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.browsePublicPosts();
        postsPage.browseAllPublicPosts_registered();
        postsPage.registered_explorePublicPost();
        postsPage.exploreAllPostsFromSameAuthor();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }

    @Test
    public void userExploreProfileOfThePostAuthor_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.browsePublicPosts();
        postsPage.browseAllPublicPosts_registered();
        postsPage.registered_explorePublicPost();
        postsPage.exploreProfileOfThePostAuthor();
        actions.assertElementPresent("profilePage.personalInformation.menu");
    }

    @Test
    public void userLikeComment_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.browsePublicPosts();
        postsPage.browseAllPublicPosts_registered();
        postsPage.registered_explorePublicPost();
        postsPage.readCommentsInPost();
        postsPage.createCommentWithThousandCharacters();
        postsPage.readCommentsInPost();
        postsPage.likeComment();
        postsPage.dislikeComment();
        postsPage.likeComment();
        actions.waitForElementClickable("postPage.dislikeComment.button");
        actions.assertElementPresent("postPage.dislikeComment.button");
    }

    @Test
    public void userDislikeCommentByAnotherUser_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.browsePublicPosts();
        postsPage.browseAllPublicPosts_registered();
        postsPage.registered_explorePublicPost();
        postsPage.readCommentsInPost();
        postsPage.createCommentWithThousandCharacters();
        postsPage.readCommentsInPost();
        postsPage.likeComment();
        postsPage.dislikeComment();
        actions.assertElementPresent("postPage.likeComment.button");
    }

    @Test
    public void userCreatePostWithOnlyText_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.createPostWithOnlyText();
        actions.assertElementPresent("postPage.exploreAllPosts.header");

    }

    @Test
    public void userCreatePostWithThousandCharacters_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.createPostWithThousandCharacters();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }

    @Test
    public void userCreateCommentWithThousandCharacters_when_LoggedIn() {
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");
        postsPage.registered_explorePublicPost();
        postsPage.createCommentWithThousandCharacters();
        actions.assertElementPresent("explorePostPage.explorePost.sign");
    }

    @Test
    public void userEditOwnPost_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.editOwnPost();
        this.actions.assertElementPresent("postPage.exploreAllPosts.header");
    }

    @Test
    public void userLikeOwnPost_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.likeOwnPost();
        this.actions.waitForElementVisible("postsPage.dislikePost.button");
        this.actions.assertElementPresent("postsPage.dislikePost.button");
    }

    @Test
    public void userDislikeOwnPost_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.dislikeOwnPost();
        this.actions.waitForElementVisible("postsPage.likePost.button");
        this.actions.assertElementPresent("postsPage.likePost.button");
    }

    @Test
    public void userCommentOwnPost_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.commentOwnPost();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }

    @Test
    public void userDeleteOwnPost_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.deleteOwnPost();
        actions.waitForElementVisible("postPage.deletePostVerification.item");
    }

    @Test
    public void userEditOwnComment_when_LoggedIn() {
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");
        postsPage.registered_explorePublicPost();
        postsPage.createCommentWithThousandCharacters();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.editOwnComment();
    }

    @Test
    public void userDeleteOwnComment_when_LoggedIn() {
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");
        postsPage.registered_explorePublicPost();
        postsPage.createCommentWithThousandCharacters();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.deleteOwnComment();
        actions.assertElementPresent("postPage.deleteComment.item");
    }

    @Test
    public void userCreatePublicPostWithTextAndPicture_when_LoggedIn() {
        loginPage.loginUser();
        postsPage.createPublicPostWithTextAndPicture();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }
}

