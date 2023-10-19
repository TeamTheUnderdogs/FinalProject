package test.cases.weare.api;

import com.weare.testframework.api.PostsAPI;
import com.weare.testframework.api.models.PostModel;
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
public class PostsAPITests extends BaseAPITest {
    private final PostsAPI api = new PostsAPI();

    private static int postId = -1;

    @BeforeAll
    public static void beforeAll() {
        postId = -1;
    }

    @AfterAll
    public static void afterAll() {
        postId = -1;
    }

    @SuppressWarnings("unchecked")
    public void verifyPostUpdate(String content, String picture, boolean isPublic) {
        Response response =
                api.getPosts(true);
        JsonPath bodyJsonPath = response.getBody().jsonPath();
        ArrayList<Object> posts = bodyJsonPath.get();
        boolean postFound = false;
        for (Object post : posts) {
            Map<String, Object> postMap = (Map<String, Object>) post;
            if (postMap.get("postId").equals(postId)) {
                assertEquals(content, postMap.get("content"));
                assertEquals(picture, postMap.get("picture"));
                assertEquals(isPublic, postMap.get("public"));
                postFound = true;
                break;
            }
        }
        assertTrue(postFound);
    }

    @Test
    @Order(1)
    public void createPostTest() {
        // Requires authentication
        authenticate();

        String content = faker.lorem().sentence(10);
        String picture = Constants.POST_DEFAULT_PICTURE;
        boolean isPublic = Constants.POST_PUBLIC;

        Response response = api.createPost(new PostModel(content, picture, isPublic));

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        // Response example:
        // {"postId":70,"content":"Post Content","picture":null,"date":"08/10/2023 18:17:30","likes":[],"comments":[],"rank":70,"public":true,"category":{"id":100,"name":"All"},"liked":false}
        JsonPath bodyJsonPath = response.getBody().jsonPath();
        String postContent = bodyJsonPath.getString("content");
        String postPicture = bodyJsonPath.getString("picture");
        boolean postPublic = bodyJsonPath.getBoolean("public");
        assertEquals(content, postContent);
        assertEquals(picture, postPicture);
        assertEquals(isPublic, postPublic);

        postId = bodyJsonPath.get("postId");

        System.out.printf("Post with id %d was created%n%n", postId);
    }

    @Test
    @Order(2)
    public void getPostsTest() {
        Response response = api.getPosts(true);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        JsonPath bodyJsonPath = response.getBody().jsonPath();
        ArrayList<Object> posts = bodyJsonPath.get();
        assertTrue(posts.size() > 0);
    }

    @Test
    @Order(2)
    public void updatePostTest() {
        // Requires authentication
        authenticate();

        String content = faker.lorem().sentence(10);
        String picture = Constants.POST_DEFAULT_PICTURE;
        boolean isPublic = Constants.POST_PUBLIC;

        Response response = api.updatePost(postId,
                new PostModel(content, picture, isPublic));

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        verifyPostUpdate(content, picture, isPublic);
        System.out.printf("Post with id %d was updated%n%n", postId);
    }

    @Test
    @Order(2)
    public void adminUpdateUserPostTest() {
        // Requires authentication
        authenticate(true);

        String content = faker.lorem().sentence(10);
        String picture = Constants.POST_DEFAULT_PICTURE;
        boolean isPublic = Constants.POST_PUBLIC;

        Response response = api.updatePost(true,
                postId, new PostModel(content, picture, isPublic));

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        verifyPostUpdate(content, picture, isPublic);
        System.out.printf("Post with id %d was updated by admin%n%n", postId);
    }

    @Test
    @Order(2)
    public void likePostTest() {
        // Requires authentication
        authenticate();

        Response response = api.likePost(postId);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        // Example response:
        // {"postId":74,"content":"Post Content","picture":"","date":"08/10/2023 19:03:39","likes":[{"userId":74,"username":"denip","expertiseProfile":{"id":74,"skills":[],"category":{"id":100,"name":"All"},"availability":0.0},"enabled":true,"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true}],"comments":[],"rank":75,"public":true,"hibernateLazyInitializer":{},"category":{"id":100,"name":"All"},"liked":true}
        JsonPath bodyJsonPath = response.getBody().jsonPath();
        ArrayList<Object> likes = bodyJsonPath.get("likes");
        boolean likedByUser = false;
        for (Object like : likes) {
            Map<String, Object> likeMap = (Map<String, Object>) like;
            String username = (String) likeMap.get("username");
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
        Response response = api.getCommentsForPost(postId);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
    }

    @Test
    @Order(3)
    public void deletePostTest() {
        // Requires authentication
        authenticate();

        Response response = api.deletePost(postId);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
    }
}
