package com.weare.testframework.api;

import com.weare.testframework.api.utils.Constants;
import com.weare.testframework.api.utils.JSONRequests;
import io.restassured.response.Response;

import static com.weare.testframework.Utils.getConfigPropertyByKey;

public class PostsAPI extends WeAreAPI {
    // API: Get posts
    public Response getPosts() {
        return getRestAssured()
                .queryParam("sorted", true)
                .get("/post/");
    }

    // API: Create a post
    public Response createPost(String content, String picture, boolean isPublic) {
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
    public Response updatePost(String content, String picture, boolean isPublic) {
        String body = String.format(JSONRequests.POST_CREATE_UPDATE, content, picture, isPublic);
        return getRestAssured()
                .queryParam("postId", Constants.POST_ID)
                .queryParam("name", getConfigPropertyByKey("social.api.username"))
                .body(body)
                .when()
                .put("/post/auth/editor")
                .then()
                .extract()
                .response();
    }

    // API: Like or Unlike a post
    public Response likePost() {
        return getRestAssured()
                .queryParam("postId", Constants.POST_ID)
                .when()
                .post("/post/auth/likesUp")
                .then()
                .extract()
                .response();
    }

    // API: Get comments for a post {{baseURL}}/api/post/Comments?postId={{postId}}
    public Response getCommentsForPost() {
        return getRestAssured()
                .queryParam("postId", Constants.POST_ID)
                .get("/post/Comments");
    }

    // API: Delete a post
    public Response deletePost() {
        return getRestAssured()
                .queryParam("postId", Constants.POST_ID)
                .when()
                .delete("/post/auth/manager")
                .then()
                .extract()
                .response();
    }
}
