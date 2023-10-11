package com.weare.testframework.api;

import com.weare.testframework.api.utils.Constants;
import com.weare.testframework.api.utils.JSONRequests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Cookies;

import static com.weare.testframework.Utils.getConfigPropertyByKey;

public class WeAreAPI {
    private Cookies authenticateCookies;

    public boolean hasAuthenticateCookies() {
        return authenticateCookies != null;
    }

    private RequestSpecification getRestAssured() {
        String baseUrl = getConfigPropertyByKey("social.api.apiUrl");
        RequestSpecification requestSpecification = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .baseUri(baseUrl);

        if (authenticateCookies != null) {
            requestSpecification.cookies(authenticateCookies);
        }
        return requestSpecification;
    }

    public void authenticateAndFetchCookies() {
        // Remove current cookies
        authenticateCookies = null;

        // Authorize to get cookies
        RestAssured.baseURI = getConfigPropertyByKey("social.api.baseUrl");
        String username = getConfigPropertyByKey("social.api.username");
        String password = getConfigPropertyByKey("social.api.password");

        Response response = RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("username", username)
                .multiPart("password", password)
                .when()
                .post("authenticate");

        Cookies cookies = response.detailedCookies();
        // The JSESSIONID cookie is the auth one
        if (cookies.get("JSESSIONID") != null) {
            authenticateCookies = cookies;
        }
        int statusCodeAuthentication = response.getStatusCode();
        System.out.println("The status code is:" + statusCodeAuthentication);
    }

    // API: Get posts
    public Response getPosts(){
        return getRestAssured()
                .queryParam("sorted", true)
                .get("/posts");
    }

    // API: Create a post
    public Response createPost(String content, String picture, boolean isPublic){
        String body = String.format(JSONRequests.POST_CREATE_UPDATE, content, picture, isPublic);
        return getRestAssured()
                .body(body)
                .when()
                .post("/post/auth/creator")
                .then()
                .extract()
                .response();
    }

    // API: Update a post
    public Response updatePost(String content, String picture, boolean isPublic){
        String body = String.format(JSONRequests.POST_CREATE_UPDATE, content, picture, isPublic);
        return getRestAssured()
                .queryParam("postId", Constants.POST_ID)
                .queryParam("name", getConfigPropertyByKey("username"))
                .body(body)
                .when()
                .put("/post/auth/editor")
                .then()
                .extract()
                .response();
    }

    // API: Like or Unlike a post
    public Response likePost(){
        return getRestAssured()
                .queryParam("postId", Constants.POST_ID)
                .when()
                .post("/post/auth/likesUp")
                .then()
                .extract()
                .response();
    }

    // API: Get comments for a post {{baseURL}}/api/post/Comments?postId={{postId}}
    public Response getCommentsForPost(){
        return getRestAssured()
                .queryParam("postId", Constants.POST_ID)
                .get("/post/Comments");
    }

    // API: Delete a post
    public Response deletePost(){
        return getRestAssured()
                .queryParam("postId", Constants.POST_ID)
                .when()
                .delete("/post/auth/manager")
                .then()
                .extract()
                .response();
    }
}
