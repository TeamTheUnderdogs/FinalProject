package com.weare.testframework.api;

import com.weare.testframework.api.utils.Endpoints;
import com.weare.testframework.api.utils.JSONRequests;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ConnectionAPI extends WeAreAPI {

    public Response sendConnectionRequest(String senderUsername, String senderPassword, int receiverId, String receiverUsername) {
        String authRequestBody = format("{"
                + "\"username\": \"%s\","
                + "\"password\": \"%s\""
                + "}",senderUsername, senderPassword);

        Cookies cookies = getRestAssured()
                .contentType(ContentType.JSON)
                .body(authRequestBody)
                .when()
                .post(Endpoints.AUTHENTICATE)
                .then()
                .statusCode(302)
                .extract().detailedCookies();

        String sendRequestBody = format(JSONRequests.SEND_REQUEST_BODY, receiverId, receiverUsername);

        return getRestAssured()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(sendRequestBody)
                .when()
                .post(JSONRequests.SEND_REQUEST_BODY);
    }

    public Response getUserConnectionRequest(String senderUsername, String senderPassword, int senderId) {
        String authRequestBody = String.format("{"
                + "\"username\": \"%s\","
                + "\"password\": \"%s\""
                + "}", senderUsername, senderPassword);

        Cookies cookies = getRestAssured()
                .body(authRequestBody)
                .when()
                .post(Endpoints.AUTHENTICATE)
                .then()
                .statusCode(302)
                .extract().detailedCookies();

        return getRestAssured()
                .cookies(cookies)
                .when()
                .get(format(Endpoints.GET_USER_REQUEST, senderId));
    }

    public Response approveConnectionRequest (String username, String password, int receiverId, int requestId){
        String authRequestBody = String.format("{"
                + "\"username\": \"%s\","
                + "\"password\": \"%s\""
                + "}", username, password);

        Cookies cookies = getRestAssured()
                .body(authRequestBody)
                .when()
                .post(Endpoints.AUTHENTICATE)
                .then()
                .statusCode(302)
                .extract().detailedCookies();

        return getRestAssured()
                .cookies(cookies)
                .when()
                .get(format(Endpoints.GET_USER_REQUEST, receiverId));

    }

    public void assertResponseRequestId(Response response, String propertyName, String expectedValue) {
        JsonPath jsonPath = response.getBody().jsonPath();
        String actualValue = jsonPath.get(propertyName);
        assertNotNull(actualValue, "The " + propertyName + " property does not exist in the resposnse.");
        assertEquals(expectedValue, actualValue, "The" + propertyName + " property in the response does not match the expected value.");
    }

    public void assertConnectionRequestIsApproved(Response response) {
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains("approved request of"));
    }
}
