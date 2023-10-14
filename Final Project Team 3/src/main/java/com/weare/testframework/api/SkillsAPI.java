package com.weare.testframework.api;

import com.weare.testframework.api.utils.Constants;
import com.weare.testframework.api.utils.JSONRequests;
import io.restassured.response.Response;

import static com.weare.testframework.Utils.getConfigPropertyByKey;

public class SkillsAPI extends WeAreAPI {
    // API: Get skills
    public Response getSkills(boolean sorted) {
        return getRestAssured()
                .queryParam("sorted", sorted)
                .get("/skill/");
    }

    // API: Create a skill
//   public Response createSkill(CategoryModel categoryModel, String skill) {
//       String body = String.format(JSONRequests.SKILL_CREATE,
//               categoryModel.getId(),
//               categoryModel.getName(),
//               skill);
//       return getRestAssured()
//               .body(body)
//               .when()
//               .post("/skill/auth/creator")
//               .then()
//               .extract()
//               .response();
//   }

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
}
