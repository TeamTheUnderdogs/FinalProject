package test.cases.weare.api;

import com.weare.testframework.api.ConnectionAPI;
import com.weare.testframework.api.WeAreAPI;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConnectionTests {

    private ConnectionAPI connectionAPI;

    @BeforeEach
    public void setUp() {
        connectionAPI = new ConnectionAPI();
    }

    @Test
    public void sendConnectionRequest() {
        String senderUsername = connectionAPI.getRandomUsername();
        String senderPassword = connectionAPI.getRandomPassword();

        //Response response = connectionAPI.approveConnectionRequest();

    }



}
