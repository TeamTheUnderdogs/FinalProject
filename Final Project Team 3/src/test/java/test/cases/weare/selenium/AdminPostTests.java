package test.cases.weare.selenium;
import org.junit.jupiter.api.Test;
import pages.weare.LoginPage;
import pages.weare.PostPage;
import pages.weare.PostsPage;

public class AdminPostTests extends BaseTest {
    PostsPage postsPage = new PostsPage(actions.getDriver());
    LoginPage loginPage = new LoginPage(actions.getDriver());
    PostPage postPage = new PostPage(actions.getDriver());

    @Test
    public void AdminSuccessfullyEditUserPublicPost_when_LoggedIn() {
        loginPage.loginAdmin();
        postsPage.registered_browseAllPublicPosts();
        postPage.registered_explorePublicPost();
        postPage.adminEditPublicPost();
        actions.assertElementPresent("postPage.assertPostEdit.element");
    }
    @Test
    public void AdminSuccessfullyDeleteUserPublicPost_when_LoggedIn() {
        loginPage.loginUser();
        postPage.registered_createPostWithOnlyText();
        loginPage.loginAdmin();
        postsPage.registered_browseAllPublicPosts();
        postPage.registered_explorePublicPost();
        postPage.adminDeletePublicPost();
        actions.assertElementPresent("postPage.deletePostVerification.item");
    }
}
