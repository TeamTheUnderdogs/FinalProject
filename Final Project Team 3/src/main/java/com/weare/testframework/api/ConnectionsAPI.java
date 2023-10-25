package com.weare.testframework.api;

import com.weare.testframework.api.utils.JSONRequests;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import static java.lang.String.format;

public class ConnectionsAPI extends WeAreAPI {

    public Response sendConnectionRequest(String authUsername, String authPassword, int userId, String username, String name) {
        Cookies authCookies = authenticateAndFetchCookies(authUsername,
                authPassword);

        String body = format(JSONRequests.CONNECTION_REQUEST_BODY, userId, username);

        return getRestAssured(authCookies)
                .queryParam("name", name)
                .body(body)
                .when()
                .post("/auth/request")
                .then()
                .extract()
                .response();
    }

    public Response getUserConnectionRequests(String authUsername, String authPassword, int userId, String name) {
        Cookies authCookies = authenticateAndFetchCookies(authUsername,
                authPassword);

        String url = format("/auth/users/%s/request/", userId);
        return getRestAssured(authCookies)
                .queryParam("name", name)
                .get(url);
    }

    public Response approveConnectionRequest(String authUsername, String authPassword, int userId, int requestId, String name) {
        Cookies authCookies = authenticateAndFetchCookies(authUsername,
                authPassword);

        String url = format("/auth/users/%s/request/approve", userId);

        return getRestAssured(authCookies)
                .queryParam("requestId", requestId)
                .queryParam("name", name)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
}
