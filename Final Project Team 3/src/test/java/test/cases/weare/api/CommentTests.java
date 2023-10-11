package test.cases.weare.api;

import com.weare.testframework.api.CommentsAPI;
import com.weare.testframework.api.PostsAPI;
import com.weare.testframework.api.WeAreAPI;
import com.weare.testframework.api.utils.Constants;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.Map;

import static com.weare.testframework.Utils.getConfigPropertyByKey;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentTests {
    private final CommentsAPI api = new CommentsAPI();
    private final PostsAPI apiPosts = new PostsAPI();

    // TODO: move to base class
    public void authenticate() {
        if (!WeAreAPI.hasAuthenticateCookies()) {
            WeAreAPI.authenticateAndFetchCookies();
            assertTrue(WeAreAPI.hasAuthenticateCookies());
        }
    }

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

    @Test
    @Order(1)
    public void createCommentTest() {
        // Requires authentication
        authenticate();
        createPostIfNeeded();

        String content = getConfigPropertyByKey("social.comment.content");
        Response response = api.createComment(content, Constants.POST_ID, Constants.USER_ID);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        // Response example:
        // {"commentId":116,"content":"Comment Content","likes":[],"date":"11/10/2023 20:01:56","liked":false}
        JsonPath bodyJsonPath = response.getBody().jsonPath();
        String commentContent = bodyJsonPath.getString("content");
        assertEquals(content, commentContent);

        Constants.COMMENT_ID = bodyJsonPath.get("commentId");
        System.out.printf("Comment with id %d was created%n%n", Constants.COMMENT_ID);
    }

    @Test
    @Order(2)
    public void getCommentsTest() {
        Response response = api.getComments();

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        JsonPath bodyJsonPath = response.getBody().jsonPath();
        ArrayList comments = bodyJsonPath.get();
        assertTrue(comments.size() > 0);
    }

    @Test
    @Order(2)
    public void updateCommentTest() {
        // Requires authentication
        authenticate();

        String content = getConfigPropertyByKey("social.comment.contentModified");
        Response response = api.updateComment(content, Constants.COMMENT_ID);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode , "Incorrect status code. Expected 200.");

        System.out.printf("Comment with id %d was updated%n%n", Constants.COMMENT_ID);
    }

    @Test
    @Order(2)
    public void likePostTest() {
        // Requires authentication
        authenticate();

        Response response = api.likeComment(Constants.COMMENT_ID);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        // Example response:
        // {"commentId":117,"content":"Comment Content","likes":[...
        JsonPath bodyJsonPath = response.getBody().jsonPath();
        ArrayList likes = bodyJsonPath.get("likes");
        boolean likedByUser = false;
        for (Object like: likes) {
            Map<String, Object> likeMap = (Map<String, Object>) like;
            String username = (String)likeMap.get("username");
            if (username.equals(getConfigPropertyByKey("social.api.username"))) {
                likedByUser = true;
                break;
            }
        }
        assertTrue(likedByUser);
    }

    @Test
    @Order(2)
    public void getCommentsForPostTest() {
        Response response = api.getCommentsForPost(Constants.POST_ID, true);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
    }

    @Test
    @Order(3)
    public void deleteCommentTest() {
        // Requires authentication
        authenticate();

        Response response = api.deleteComment(Constants.COMMENT_ID);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
        Constants.COMMENT_ID = -1;
    }
}
