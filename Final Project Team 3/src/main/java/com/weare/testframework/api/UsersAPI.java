package com.weare.testframework.api;

import com.weare.testframework.api.utils.JSONRequests;
import io.restassured.response.Response;

public class UsersAPI extends WeAreAPI {
    // API: Register a new user
  //  public Response registerUser(CategoryModel categoryModel,
  //                               String confirmPassword,
  //                               String email,
  //                               String password,
  //                               String username) {
  //      String body = String.format(JSONRequests.USER_REGISTER,
  //              categoryModel.getId(),
  //              categoryModel.getName(),
  //              confirmPassword,
  //              email,
  //              password,
  //              username);
  //      return getRestAssured()
  //              .body(body)
  //              .when()
  //              .post("/users/")
  //              .then()
  //              .extract()
  //              .response();
  //  }

    // API: Get user by id
    public Response getUser(int userId) {
        String url = String.format("/api/users/auth/%s", userId);
        return getRestAssured()
                .get(url);
    }

   // API: Show profile posts
//   public Response getProfilePosts(int userId, PageModel model) {
//       String url = String.format("/api/users/auth/%s/posts", userId);
//       String body = String.format(JSONRequests.PAGE,
//               model.getIndex(),
//               model.getNext(),
//               model.getSearchParam1(),
//               model.getSearchParam2(),
//               model.getSize());
//       return getRestAssured()
//               .body(body)
//               .get(url);
//   }
}
