package test.cases.weare.api;

import com.weare.testframework.api.ConnectionsAPI;
import com.weare.testframework.api.WeAreAPI;
import com.weare.testframework.api.models.UserModel;
import com.weare.testframework.api.utils.Constants;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConnectionsAPITests extends BaseAPITest {

    private ConnectionsAPI api = new ConnectionsAPI();
    private static int requestId = -1;
    private static UserModel anotherUser;

    @BeforeAll
    public static void beforeAll() {
        if (anotherUser == null) {
            authenticate();
            anotherUser = WeAreAPI.registerNewUser(Constants.ROLE_USER);
        }
    }

    @Test
    @Order(1)
    public void sendConnectionRequestTest() {
        Response response = api.sendConnectionRequest(Constants.USER.getUsername(),
                Constants.USER.getPassword(),
                anotherUser.getUserId(),
                anotherUser.getUsername(),
                anotherUser.getUsername());

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        // Response example:
        // Pamula send friend request to Curtis
        String responseStr = response.getBody().asString();
        assertTrue(responseStr.contains(Constants.SEND_REQUEST_MESSAGE));

        String[] parts = responseStr.split(" ");
        assertEquals(Constants.USER.getUsername(), parts[0]);
        assertEquals(anotherUser.getUsername(), parts[parts.length - 1]);
    }

    @Test
    @Order(2)
    public void getUserRequestsTest() {
        Response response = api.getUserConnectionRequests(anotherUser.getUsername(),
                anotherUser.getPassword(), anotherUser.getUserId(), anotherUser.getUsername());

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        // Response example:
        // [{"id":42,"approved":false,"seen":false,"timeStamp":"18/10/2023 17:44"}]
        JsonPath bodyJsonPath = response.getBody().jsonPath();
        ArrayList<Object> requests = bodyJsonPath.get();

        // This is a new user, so there should be one request only
        assertEquals(1, requests.size());

        // Get the requestId
        Map<String, Object> requestMap = (Map<String, Object>) requests.get(0);
        assertNotNull(requestMap.get("id"));
        requestId = (int) requestMap.get("id");
    }

    @Test
    @Order(3)
    public void approveTest() {
        Response response = api.approveConnectionRequest(anotherUser.getUsername(),
                anotherUser.getPassword(), anotherUser.getUserId(), requestId, anotherUser.getUsername());

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        // Response example:
        // Azalee approved request of Preston
        String responseStr = response.getBody().asString();
        assertTrue(responseStr.contains(Constants.APPROVE_REQUEST_MESSAGE));

        String[] parts = responseStr.split(" ");
        assertEquals(anotherUser.getUsername(), parts[0]);
        assertEquals(Constants.USER.getUsername(), parts[parts.length - 1]);
    }
}
