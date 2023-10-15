package test.cases.weare.api;

import com.weare.testframework.api.PostsAPI;
import com.weare.testframework.api.WeAreAPI;
import com.weare.testframework.api.utils.Constants;
import io.restassured.response.Response;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
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

    private final PostsAPI apiPosts = new PostsAPI();

    public void createPostIfNeeded() {
        if (Constants.POST_ID != -1) {
            return;
        }

        String content = getConfigPropertyByKey("social.post.content");
        String picture = getConfigPropertyByKey("social.post.picture");
        boolean isPublic = Boolean.parseBoolean(getConfigPropertyByKey("social.post.public"));

        Response response = apiPosts.createPost(content, picture, isPublic);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
        Constants.POST_ID = response.getBody().jsonPath().get("postId");
    }

    public String getRandomSkill() {
        String skill = faker.job().keySkills();
        return String.format("%s %d", skill, faker.random().nextInt(1, 999));
    }
}
