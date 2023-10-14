package com.weare.testframework.api;

import com.weare.testframework.api.utils.Endpoints;
import com.weare.testframework.api.utils.JSONRequests;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import static java.lang.String.format;


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
}