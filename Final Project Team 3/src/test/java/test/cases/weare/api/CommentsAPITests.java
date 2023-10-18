package test.cases.weare.api;

import com.weare.testframework.api.CommentsAPI;
import com.weare.testframework.api.utils.Constants;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Map;

import static com.weare.testframework.api.WeAreAPI.faker;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentsAPITests extends BaseAPITest {
    private final CommentsAPI api = new CommentsAPI();
    private static int postId = -1;
    private static int commentId = -1;
    private static boolean postCreated = false;
    private static String commentContent;

    @BeforeAll
    public static void beforeAll() {
        postId = -1;
        commentId = -1;
        commentContent = "";
    }

    @AfterAll
    public static void afterAll() {
        if (postCreated) {
            authenticate();
            deletePost(postId);
            postCreated = false;
            postId = -1;
        }
        commentId = -1;
        commentContent = "";
    }

    @SuppressWarnings("unchecked")
    public void verifyCommentUpdate(String content) {
        Response commentsResponse =
                api.getCommentsForPost(postId, true);
        JsonPath bodyJsonPath = commentsResponse.getBody().jsonPath();
        ArrayList<Object> comments = bodyJsonPath.get();
        boolean commentFound = false;
        for (Object comment: comments) {
            Map<String, Object> commentMap = (Map<String, Object>) comment;
            if (commentMap.get("commentId").equals(commentId)) {
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

        // Create post to comment on
        if (postId == -1) {
            postId = createPost();
            postCreated = true;
        }

        String content = faker.lorem().sentence(5);
        Response response = api.createComment(content, postId, Constants.USER.getUserId());

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        // Response example:
        // {"commentId":116,"content":"Comment Content","likes":[],"date":"11/10/2023 20:01:56","liked":false}
        JsonPath bodyJsonPath = response.getBody().jsonPath();
        String commentContent = bodyJsonPath.getString("content");
        assertEquals(content, commentContent);
        CommentsAPITests.commentContent = commentContent;
        commentId = bodyJsonPath.get("commentId");
        System.out.printf("Comment with id %d was created%n%n", commentId);
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
        Response response = api.updateComment(content, commentId);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode , "Incorrect status code. Expected 200.");

        CommentsAPITests.commentContent = content;
        verifyCommentUpdate(content);
        System.out.printf("Comment with id %d was updated%n%n", commentId);
    }

    @Test
    @Order(2)
    public void adminUpdateUserCommentTest() {
        // Requires authentication
        authenticate(true);

        String content = faker.lorem().sentence(5);
        Response response = api.updateComment(content, commentId);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        CommentsAPITests.commentContent = content;
        verifyCommentUpdate(content);
        System.out.printf("Comment with id %d was updated by admin%n%n", commentId);
    }

    @Test
    @Order(2)
    public void likePostTest() {
        // Requires authentication
        authenticate();

        Response response = api.likeComment(commentId);

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
        Response response = api.getCommentsForPost(postId, true);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        JsonPath bodyJsonPath = response.getBody().jsonPath();
        ArrayList<Object> comments = bodyJsonPath.get();
        assertTrue(comments.size() > 0);
    }

    @Test
    @Order(2)
    public void getCommentTest() {
        Response response = api.getComment(commentId);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        // Response example:
        // {"commentId":166,"content":"Voluptas aut voluptatem id voluptate nemo ea saepe.","likes":[{"userId":295,"username":"Johana"...
        JsonPath bodyJsonPath = response.getBody().jsonPath();
        String commentContent = bodyJsonPath.getString("content");
        assertEquals(CommentsAPITests.commentContent, commentContent);
        assertEquals(commentId, (Integer) bodyJsonPath.get("commentId"));
    }

    @Test
    @Order(3)
    public void deleteCommentTest() {
        // Requires authentication
        authenticate();

        Response response = api.deleteComment(commentId);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
    }
}
