package test.cases.weare.selenium;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.weare.LoginPage;
import pages.weare.PostPage;
import pages.weare.PostsPage;

public class UserCommentTests extends BaseTest {
    PostsPage postsPage = new PostsPage(actions.getDriver());
    LoginPage loginPage = new LoginPage(actions.getDriver());
    PostPage postPage = new PostPage(actions.getDriver());

    @BeforeEach
    public void loginUser() {
        loginPage.loginUser();
    }

    @Test
    public void userReadCommentInPost_when_LoggedIn() {
        postsPage.browsePublicPosts();
        postsPage.registered_browseAllPublicPosts();
        postPage.registered_explorePublicPost();
        postPage.registered_readCommentsInPost();
        actions.assertElementPresent("postPage.showNumberOfLikesForComment.element");
    }
    @Test
    public void userLikeCommentByAnotherUser_when_LoggedIn() {
        postPage.registered_createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.browsePublicPosts();
        postsPage.registered_browseAllPublicPosts();
        postPage.registered_explorePublicPost();
        postPage.registered_readCommentsInPost();
        postPage.registered_createCommentWithThousandCharacters();
        postPage.registered_readCommentsInPost();
        postPage.registered_likeComment();
        postPage.registered_dislikeComment();
        postPage.registered_likeComment();
        actions.waitForElementClickable("postPage.dislikeComment.button");
        actions.assertElementPresent("postPage.dislikeComment.button");
    }
    @Test
    public void userDislikeCommentByAnotherUser_when_LoggedIn() {
        postPage.registered_createPostWithOnlyText();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postsPage.browsePublicPosts();
        postsPage.registered_browseAllPublicPosts();
        postPage.registered_explorePublicPost();
        postPage.registered_readCommentsInPost();
        postPage.registered_createCommentWithThousandCharacters();
        postPage.registered_readCommentsInPost();
        postPage.registered_likeComment();
        postPage.registered_dislikeComment();
        actions.assertElementPresent("postPage.likeComment.button");
    }
    @Test
    public void userCreateCommentWithThousandCharacters_when_LoggedIn() {
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");
        postPage.registered_explorePublicPost();
        postPage.registered_createCommentWithThousandCharacters();
        actions.assertElementPresent("explorePostPage.explorePost.sign");
    }
    @Test
    public void userEditOwnComment_when_LoggedIn() {
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");
        postPage.registered_explorePublicPost();
        postPage.registered_createCommentWithThousandCharacters();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postPage.registered_editOwnComment();
    }
    @Test
    public void userDeleteOwnComment_when_LoggedIn() {
        actions.waitForElementClickable("homePage.latestPosts.button");
        actions.clickElement("homePage.latestPosts.button");
        postPage.registered_explorePublicPost();
        postPage.registered_createCommentWithThousandCharacters();
        actions.waitForElementClickable("homePage.home.button");
        actions.clickElement("homePage.home.button");
        postPage.registered_deleteOwnComment();
        actions.assertElementPresent("postPage.deleteComment.item");
    }
}
