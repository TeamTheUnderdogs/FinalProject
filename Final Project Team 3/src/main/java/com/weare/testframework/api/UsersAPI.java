package com.weare.testframework.api;

import com.weare.testframework.api.models.CategoryModel;
import com.weare.testframework.api.models.ExpertiseModel;
import com.weare.testframework.api.models.PageModel;
import com.weare.testframework.api.models.PersonalModel;
import com.weare.testframework.api.utils.JSONRequests;
import io.restassured.response.Response;

public class UsersAPI extends WeAreAPI {
    // API: Get users
    public Response getUsers(PageModel model) {
            String body = String.format(JSONRequests.PAGE_BODY,
                    model.getIndex(),
                    model.getNext(),
                    model.getSearchParam1(),
                    model.getSearchParam2(),
                    model.getSize());
            return getRestAssured()
                    .body(body)
                    .post("users");
    }

    // API: Register a new user
    public Response registerUser(String userRole, CategoryModel categoryModel,
                                 String confirmPassword,
                                 String email,
                                 String password,
                                 String username) {
        String body = String.format(JSONRequests.USER_REGISTER_BODY,
                userRole,
                categoryModel.getId(),
                categoryModel.getName(),
                confirmPassword,
                email,
                password,
                username);
        return getRestAssured()
                .body(body)
                .when()
                .post("/users/")
                .then()
                .extract()
                .response();
    }

    // API: Get user by id
    public Response getUser(int userId, String username) {
        String url = String.format("/users/auth/%s", userId);
        return getRestAssured()
                .queryParam("principal", username)
                .get(url);
    }

    // API: Show profile posts
    public Response getProfilePosts(int userId, PageModel model) {
        String url = String.format("/users/%s/posts", userId);
        String body = String.format(JSONRequests.PAGE_BODY,
                model.getIndex(),
                model.getNext(),
                model.getSearchParam1(),
                model.getSearchParam2(),
                model.getSize());
        return getRestAssured()
                .body(body)
                .get(url);
    }

    // API: Upgrade user expertise
    public Response upgradeExpertise(int userId, ExpertiseModel model) {
        String url = String.format("/users/auth/%s/expertise", userId);
        String skillsArray = WeAreAPI.arrayToJSONString(model.getSkills());

        String body = String.format(JSONRequests.EXPERTISE_PROFILE_BODY,
                model.getAvailability(),
                model.getCategory().getId(),
                model.getCategory().getName(),
                model.getId(),
                model.getSkill1(),
                model.getSkill2(),
                skillsArray);
        return getRestAssured()
                .body(body)
                .post(url);
    }

    // API: Upgrade user personal profile
    public Response upgradePersonal(int userId, PersonalModel model) {
        String url = String.format("/users/auth/%s/personal", userId);
        String body = String.format(JSONRequests.PERSONAL_PROFILE_BODY,
                model.getBirthYear(),
                model.getFirstName(),
                model.getId(),
                model.getLastName(),
                model.getLocation().getCity().getCity(),
                model.getLocation().getCity().getId(),
                model.getPersonalReview(),
                model.getPicture(),
                model.isPicturePrivacy(),
                model.getSex());
        return getRestAssured()
                .body(body)
                .post(url);
    }
}