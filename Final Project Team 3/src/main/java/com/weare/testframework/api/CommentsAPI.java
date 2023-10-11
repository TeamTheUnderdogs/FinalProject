package com.weare.testframework.api;

import com.weare.testframework.api.utils.Constants;
import com.weare.testframework.api.utils.JSONRequests;
import io.restassured.response.Response;

import static com.weare.testframework.Utils.getConfigPropertyByKey;

public class CommentsAPI extends WeAreAPI {
    // API: Get comments
    public Response getComments() {
        return getRestAssured()
                .queryParam("sorted", true)
                .get("/comment/");
    }

    // API: Get comments by post
    public Response getCommentsForPost(int postId, boolean sorted) {
        return getRestAssured()
                .queryParam("sorted", sorted)
                .queryParam("postId", postId)
                .get("/comment/byPost");
    }

    // API: Get single comment
    public Response getComment(int commentId) {
        return getRestAssured()
                .queryParam("commentId", commentId)
                .get("/comment/single");
    }

    // API: Create a comment
    public Response createComment(String content, int postId, int userId) {
        String body = String.format(JSONRequests.COMMENT_CREATE_UPDATE, content, postId, userId);
        return getRestAssured()
                .body(body)
                .when()
                .post("/comment/auth/creator")
                .then()
                .extract()
                .response();
    }

    // API: Update a comment
    public Response updateComment(String content, int commentId) {
        return getRestAssured()
                .queryParam("commentId", commentId)
                .queryParam("content", content)
                .when()
                .put("/comment/auth/editor")
                .then()
                .extract()
                .response();
    }

    // API: Like or Unlike a comment
    public Response likeComment(int commentId) {
        return getRestAssured()
                .queryParam("commentId", commentId)
                .when()
                .post("/comment/auth/likesUp")
                .then()
                .extract()
                .response();
    }

    // API: Delete a comment
    public Response deleteComment(int commentId) {
        return getRestAssured()
                .queryParam("commentId", commentId)
                .when()
                .delete("/comment/auth/manager")
                .then()
                .extract()
                .response();
    }
}
