package test.cases.weare.api;

import com.weare.testframework.api.SkillsAPI;
import com.weare.testframework.api.utils.Constants;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;

import static com.weare.testframework.api.WeAreAPI.faker;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SkillTests extends BaseAPITest {
    private final SkillsAPI api = new SkillsAPI();

    @Test
    @Order(1)
    public void createSkillTest() {
        // Requires authentication
        authenticate();

        String skill = getRandomSkill();
        Constants.SKILL = skill;
        Response response = api.createSkill(Constants.CATEGORY_ALL, skill);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        // Response example:
        // {"skillId":115,"skill":"Problem solving","category":{"id":100,"name":"All"}}
        JsonPath bodyJsonPath = response.getBody().jsonPath();
        String skillSkill = bodyJsonPath.getString("skill");
        int categoryId = bodyJsonPath.getInt("category.id");
        assertEquals(skill, skillSkill);
        assertEquals(Constants.CATEGORY_ALL.getId(), categoryId);

        Constants.SKILL_ID = bodyJsonPath.get("skillId");

        System.out.printf("Skill with id %d was created%n%n", Constants.SKILL_ID);
    }

    @Test
    @Order(2)
    public void getSkillsTest() {
        Response response = api.getSkills(true);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        JsonPath bodyJsonPath = response.getBody().jsonPath();
        ArrayList skills = bodyJsonPath.get();
        assertTrue(skills.size() > 0);
    }

    @Test
    @Order(2)
    public void getSkillTest() {
        Response response = api.getSkill(Constants.SKILL_ID);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
        // Response example:
        // {"skillId":116,"skill":"Leadership","category":{"id":100,"name":"All"},"hibernateLazyInitializer":{}}
        JsonPath bodyJsonPath = response.getBody().jsonPath();
        int skillId = bodyJsonPath.get("skillId");
        String skill = bodyJsonPath.get("skill");
        assertEquals(Constants.SKILL_ID, skillId);
        assertEquals(Constants.SKILL, skill);
    }

    @Test
    @Order(2)
    public void updateSkillTest() {
        // Requires authentication
        authenticate();

        String skill = getRandomSkill();
        Constants.SKILL = skill;
        Response response = api.updateSkill(Constants.SKILL_ID, skill);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        System.out.printf("Skill with id %d was updated%n%n", Constants.SKILL_ID);
    }

    @Test
    @Order(3)
    public void deleteSkillTest() {
        // Requires authentication
        authenticate();

        Response response = api.deleteSkill(Constants.SKILL_ID);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
        Constants.SKILL_ID = -1;
    }
}
