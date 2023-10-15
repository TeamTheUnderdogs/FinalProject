package com.weare.testframework.api;

import com.weare.testframework.api.models.PostModel;
import com.weare.testframework.api.utils.Constants;
import com.weare.testframework.api.utils.JSONRequests;
import io.restassured.response.Response;

import static com.weare.testframework.Utils.getConfigPropertyByKey;

public class PostsAPI extends WeAreAPI {
    // API: Get posts
    public Response getPosts(boolean sorted) {
        return getRestAssured()
                .queryParam("sorted", sorted)
                .get("/post/");
    }

    // API: Create a post
    public Response createPost(PostModel post) {
        String body = String.format(JSONRequests.POST_CREATE_UPDATE_BODY,
                post.getContent(), post.getPicture(), post.isPublic());
        return getRestAssured()
                .body(body)
                .when()
                .post("/post/auth/creator")
                .then()
                .extract()
                .response();
    }

    // API: Update a post
    public Response updatePost(int postId, PostModel post) {
        String body = String.format(JSONRequests.POST_CREATE_UPDATE_BODY,
                post.getContent(), post.getPicture(), post.isPublic());
        return getRestAssured()
                .queryParam("postId", postId)
                .queryParam("name", getConfigPropertyByKey("social.api.username"))
                .body(body)
                .when()
                .put("/post/auth/editor")
                .then()
                .extract()
                .response();
    }

    // API: Like or Unlike a post
    public Response likePost(int postId) {
        return getRestAssured()
                .queryParam("postId", postId)
                .when()
                .post("/post/auth/likesUp")
                .then()
                .extract()
                .response();
    }

    // API: Get comments for a post
    public Response getCommentsForPost(int postId) {
        return getRestAssured()
                .queryParam("postId", postId)
                .get("/post/Comments");
    }

    // API: Delete a post
    public Response deletePost(int postId) {
        return getRestAssured()
                .queryParam("postId", postId)
                .when()
                .delete("/post/auth/manager")
                .then()
                .extract()
                .response();
    }
}
