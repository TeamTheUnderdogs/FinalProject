package test.cases.weare.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import pages.weare.LoginPage;
import pages.weare.PostsPage;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static com.weare.testframework.Utils.getWebDriver;


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
        postsPage.browsePublicPosts();
        ;
        postsPage.anonymous_browsePublicPostsByCategory();
        Assertions.assertEquals(getConfigPropertyByKey("social.app.postsByCategoryPage"), getWebDriver().getCurrentUrl(),
                "Page not successfully navigated");
        actions.assertElementPresent("postsPage.assertSearchByCategory.element");
    }

    @Test
    public void browsePublicPostsByCategory_when_Logged() {
        loginPage.loginUser();
       postsPage.browsePublicPosts();
        postsPage.registered_browsePublicPostsByCategory();
        Assertions.assertEquals(getConfigPropertyByKey("social.app.postsByCategoryPage"), getWebDriver().getCurrentUrl(),
                "Page not successfully navigated");

        actions.assertElementPresent("postsPage.assertSearchByCategory.element");


    }

    @Test

    public void explorePublicPost_when_Logged (){
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");
        postsPage.registered_explorePublicPost();
        actions.assertElementPresent("explorePostPage.explorePost.sign");
    }

    @Test
    public void userLikePublicPost() {
        loginPage.loginUser();
        postsPage.createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.registered_likePublicPost();
        actions.assertElementAttribute("postsPage.dislikePost.button",
                "value", "Dislike");
    }

    @Test
    public void userDislikePublicPost() {
        loginPage.loginUser();
        postsPage.createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.registered_likePublicPost();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.registered_dislikePublicPost();
        actions.assertElementAttribute("postsPage.likePost.button", "value", "Like");
    }

    @Test
    public void AdminSuccessfullyEditUserPublicPost() {
        loginPage.loginAdmin();
        postsPage.browseAllPublicPosts_registered();
        postsPage.registered_explorePublicPost();
        postsPage.adminEditPublicPost();
        actions.assertElementPresent("postPage.assertPostEdit.element");


    }

    @Test
    public void userReadCommentInPost() {
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
    public void userLikeComment() {
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
    public void userDislikeComment() {
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
    public void userCreatePostWithOnlyText() {
        loginPage.loginUser();
        postsPage.createPostWithOnlyText();
        actions.assertElementPresent("postPage.exploreAllPosts.header");

    }

    @Test
    public void userCreatePostWithThousandCharacters() {
        loginPage.loginUser();
        postsPage.createPostWithThousandCharacters();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }

    @Test
    public void userCreateCommentWithThousandCharacters() {
        loginPage.loginUser();
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");
        postsPage.registered_explorePublicPost();
        postsPage.createCommentWithThousandCharacters();
        actions.assertElementPresent("explorePostPage.explorePost.sign");
    }

    @Test
    public void userEditOwnPost() {
        loginPage.loginUser();
        postsPage.editOwnPost();
        this.actions.assertElementPresent("postPage.exploreAllPosts.header");
    }

    @Test
    public void userLikeOwnPost() {
        loginPage.loginUser();
        postsPage.createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.likeOwnPost();
        this.actions.waitForElementVisible("postsPage.dislikePost.button");
        this.actions.assertElementPresent("postsPage.dislikePost.button");
    }

    @Test
    public void userDislikeOwnPost() {
        loginPage.loginUser();
        postsPage.createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.dislikeOwnPost();
        this.actions.waitForElementVisible("postsPage.likePost.button");
        this.actions.assertElementPresent("postsPage.likePost.button");
    }

    @Test
    public void userCommentOwnPost() {
        loginPage.loginUser();
        postsPage.commentOwnPost();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }

    @Test
    public void userDeleteOwnPost() {
        loginPage.loginUser();
        postsPage.deleteOwnPost();
        actions.waitForElementVisible("postPage.deletePostVerification.item");
    }

    @Test
    public void userEditOwnComment() {
        loginPage.loginUser();
        postsPage.editOwnComment();
    }

    @Test
    public void userDeleteOwnComment() {
        loginPage.loginUser();
        postsPage.deleteOwnComment();
        actions.assertElementPresent("postPage.deleteComment.item");

    }

    @Test
    public void userCreatePublicPostWithTextAndPicture() {
       loginPage.loginUser();
        postsPage.createPublicPostWithTextAndPicture();
        actions.assertElementPresent("postPage.exploreAllPosts.header");
    }
}

