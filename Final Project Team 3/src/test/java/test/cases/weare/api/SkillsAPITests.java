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

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SkillsAPITests extends BaseAPITest {
    private final SkillsAPI api = new SkillsAPI();

    public static int skillId = -1;

    public static String currentSkill;

    @Test
    @Order(1)
    public void createSkillTest() {
        // Requires authentication
        authenticate();

        String skill = getRandomSkill();
        currentSkill = skill;
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

        skillId = bodyJsonPath.get("skillId");

        System.out.printf("Skill with id %d was created%n%n", skillId);
    }

    @Test
    @Order(2)
    public void getSkillsTest() {
        Response response = api.getSkills(true);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        JsonPath bodyJsonPath = response.getBody().jsonPath();
        ArrayList<Object> skills = bodyJsonPath.get();
        assertTrue(skills.size() > 0);
    }

    @Test
    @Order(2)
    public void getSkillTest() {
        Response response = api.getSkill(skillId);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
        // Response example:
        // {"skillId":116,"skill":"Leadership","category":{"id":100,"name":"All"},"hibernateLazyInitializer":{}}
        JsonPath bodyJsonPath = response.getBody().jsonPath();
        int skillIdActual = bodyJsonPath.get("skillId");
        String skill = bodyJsonPath.get("skill");
        assertEquals(skillId, skillIdActual);
        assertEquals(currentSkill, skill);
    }

    @Test
    @Order(2)
    public void updateSkillTest() {
        // Requires authentication
        authenticate();

        String skill = getRandomSkill();
        currentSkill = skill;
        Response response = api.updateSkill(skillId, skill);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        // Verify content is updated
        Response getSkillResponse = api.getSkill(skillId);
        String updatedSkill =
                getSkillResponse.getBody().jsonPath().get("skill");
        assertEquals(currentSkill, updatedSkill);

        System.out.printf("Skill with id %d was updated%n%n", skillId);
    }

    @Test
    @Order(3)
    public void deleteSkillTest() {
        // Requires authentication
        authenticate();

        Response response = api.deleteSkill(skillId);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
        skillId = -1;
    }
}
