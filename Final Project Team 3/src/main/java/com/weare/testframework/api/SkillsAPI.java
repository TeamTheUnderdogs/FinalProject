package com.weare.testframework.api;

import com.weare.testframework.api.models.CategoryModel;
import com.weare.testframework.api.utils.JSONRequests;
import io.restassured.response.Response;

public class SkillsAPI extends WeAreAPI {
    // API: Get skills
    public Response getSkills(boolean sorted) {
        return getRestAssured()
                .queryParam("sorted", sorted)
                .get("/skill/");
    }

    // API: Create a skill
    public Response createSkill(CategoryModel categoryModel, String skill) {
        String body = String.format(JSONRequests.SKILL_CREATE_BODY,
                categoryModel.getId(),
                categoryModel.getName(),
                skill);
        return getRestAssured()
                .body(body)
                .when()
                .post("/skill/create")
                .then()
                .extract()
                .response();
    }

    // API: Update a skill
    public Response updateSkill(int skillId, String skill) {
        return getRestAssured()
                .queryParam("skillId", skillId)
                .queryParam("skill", skill)
                .when()
                .put("/skill/edit")
                .then()
                .extract()
                .response();
    }

    // API: Delete a skill
    public Response deleteSkill(int skillId) {
        return getRestAssured()
                .queryParam("skillId", skillId)
                .when()
                .put("/skill/delete")
                .then()
                .extract()
                .response();
    }

    // API: Get a skill by id
    public Response getSkill(int skillId) {
        return getRestAssured()
                .queryParam("skillId", skillId)
                .get("/skill/getOne");
    }
}
