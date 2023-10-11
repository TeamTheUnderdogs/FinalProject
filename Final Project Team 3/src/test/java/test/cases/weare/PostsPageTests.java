package test.cases.weare;

import com.weare.testframework.UserActions;
import org.junit.jupiter.api.Test;

import pages.weare.PostsPage;


public class PostsPageTests extends BaseTest{


PostsPage postsPage = new PostsPage(actions.getDriver());
    @Test
    public void browsePublicPosts_when_Anonymous (){
        postsPage.browsePublicPosts();

        UserActions userActions = new UserActions();
        userActions.assertElementPresent("postsPage.browsePostsByCategory.button");
    }
}
