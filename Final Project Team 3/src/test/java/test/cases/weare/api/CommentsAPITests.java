package test.cases.weare.api;

import com.weare.testframework.api.CommentsAPI;
import com.weare.testframework.api.utils.Constants;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.Map;

import static com.weare.testframework.api.WeAreAPI.faker;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentsAPITests extends BaseAPITest {
    private final CommentsAPI api = new CommentsAPI();

    public void verifyCommentUpdate(String content) {
        Response commentsResponse =
                api.getCommentsForPost(Constants.POST_ID, true);
        JsonPath bodyJsonPath = commentsResponse.getBody().jsonPath();
        ArrayList<Object> comments = bodyJsonPath.get();
        boolean commentFound = false;
        for (Object comment: comments) {
            Map<String, Object> commentMap = (Map<String, Object>) comment;
            if (commentMap.get("commentId").equals(Constants.COMMENT_ID)) {
                assertEquals(content, commentMap.get("content"));
                commentFound = true;
                break;
            }
        }
        assertTrue(commentFound);
    }

    @Test
    @Order(1)
    public void createCommentTest() {
        // Requires authentication
        authenticate();
        createPostIfNeeded();

        String content = faker.lorem().sentence(5);
        Response response = api.createComment(content, Constants.POST_ID, Constants.USER.getUserId());

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
        ArrayList<Object> comments = bodyJsonPath.get();
        assertTrue(comments.size() > 0);
    }

    @Test
    @Order(2)
    public void updateCommentTest() {
        // Requires authentication
        authenticate();

        String content = faker.lorem().sentence(5);
        Response response = api.updateComment(content, Constants.COMMENT_ID);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode , "Incorrect status code. Expected 200.");

        System.out.printf("Comment with id %d was updated%n%n", Constants.COMMENT_ID);
        verifyCommentUpdate(content);
    }

    @Test
    @Order(2)
    public void adminUpdateUserCommentTest() {
        // Requires authentication
        authenticate(true);

        String content = faker.lorem().sentence(5);
        Response response = api.updateComment(content, Constants.COMMENT_ID);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        System.out.printf("Comment with id %d was updated by admin%n%n", Constants.POST_ID);
        verifyCommentUpdate(content);
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
        ArrayList<Object> likes = bodyJsonPath.get("likes");
        boolean likedByUser = false;
        for (Object like: likes) {
            Map<String, Object> likeMap = (Map<String, Object>) like;
            String username = (String)likeMap.get("username");
            if (username.equals(Constants.USER.getUsername())) {
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

        JsonPath bodyJsonPath = response.getBody().jsonPath();
        ArrayList<Object> comments = bodyJsonPath.get();
        assertTrue(comments.size() > 0);
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

        deletePostIfNeeded();
    }
}
