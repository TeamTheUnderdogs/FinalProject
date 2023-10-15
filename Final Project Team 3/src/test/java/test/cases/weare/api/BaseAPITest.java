package test.cases.weare.api;

import com.weare.testframework.api.PostsAPI;
import com.weare.testframework.api.WeAreAPI;
import com.weare.testframework.api.models.PostModel;
import com.weare.testframework.api.utils.Constants;
import io.restassured.response.Response;

import static com.weare.testframework.api.WeAreAPI.faker;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseAPITest {

    // Authenticates user and ensures cookies are set
    public void authenticate() {
        if (!WeAreAPI.hasAuthenticateCookies()) {
            WeAreAPI.authenticateAndFetchCookies();
            assertTrue(WeAreAPI.hasAuthenticateCookies());
        }
    }

    public void authenticate(boolean admin) {
        if (admin) {
            if (!WeAreAPI.hasAdminAuthenticateCookies()) {
                WeAreAPI.authenticateAndFetchCookies(true);
                assertTrue(WeAreAPI.hasAdminAuthenticateCookies());
            }
        } else {
            if (!WeAreAPI.hasAuthenticateCookies()) {
                WeAreAPI.authenticateAndFetchCookies();
                assertTrue(WeAreAPI.hasAuthenticateCookies());
            }
        }
    }

    private static boolean postCreated = false;

    public void createPostIfNeeded() {
        if (Constants.POST_ID != -1) {
            return;
        }

        PostsAPI apiPosts = new PostsAPI();
        String content = faker.lorem().sentence(10);
        String picture = Constants.POST_DEFAULT_PICTURE;
        boolean isPublic = Constants.POST_PUBLIC;

        Response response = apiPosts.createPost(new PostModel(content, picture, isPublic));

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
        Constants.POST_ID = response.getBody().jsonPath().get("postId");
        postCreated = true;
    }

    public void deletePostIfNeeded() {
        if (postCreated) {
            PostsAPI apiPosts = new PostsAPI();
            Response response = apiPosts.deletePost(Constants.POST_ID);

            int statusCode = response.getStatusCode();
            assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
            Constants.POST_ID = -1;
        }
        postCreated = false;
    }

    public String getRandomSkill() {
        String skill = faker.job().keySkills();
        long tm = System.currentTimeMillis();
        return String.format("%s %d", skill, tm);
    }
}
