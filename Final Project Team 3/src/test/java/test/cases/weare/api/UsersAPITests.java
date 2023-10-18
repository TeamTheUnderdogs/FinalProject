package test.cases.weare.api;

import com.weare.testframework.api.UsersAPI;
import com.weare.testframework.api.models.ExpertiseModel;
import com.weare.testframework.api.models.PersonalModel;
import com.weare.testframework.api.utils.Constants;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import static com.weare.testframework.api.WeAreAPI.faker;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsersAPITests extends BaseAPITest {
    private final UsersAPI api = new UsersAPI();

    private static int postId = -1;
    private static boolean postCreated = false;

    @AfterAll
    public static void afterAll() {
        if (postCreated) {
            authenticate();
            deletePost(postId);
        }
        postCreated = false;
        postId = -1;
    }

    @Test
    @Order(1)
    public void registerUserTest() {
        String password = faker.internet().password(8, 12);
        String email = faker.internet().emailAddress();
        String username = faker.name().firstName();
        Response response = api.registerUser(Constants.ROLE_USER,
                Constants.CATEGORY_ALL,
                password, email, password, username);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        // Response example:
        // User with name Buddy and id 86 was created
        String responseBody = response.getBody().print();
        String[] splitByIntervals = responseBody.split(" ");
        String usernameActual = splitByIntervals[Constants.USERNAME_INDEX];
        int userId = Integer.parseInt(splitByIntervals[Constants.USER_ID_INDEX]);

        assertEquals(username, usernameActual);

        System.out.printf("User with id %d was created%n%n", userId);
    }

    @Test
    @Order(2)
    public void getUsersTest() {
        // Requires authentication
        authenticate();

        Response response = api.getUsers(Constants.PAGE);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        JsonPath bodyJsonPath = response.getBody().jsonPath();
        ArrayList<Object> users = bodyJsonPath.get();
        assertTrue(users.size() > 0);
    }

    @Test
    @Order(2)
    public void getUserByIdTest() {
        // Requires authentication
        authenticate();

        Response response = api.getUser(Constants.USER.getUserId(), Constants.USER.getUsername());

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        JsonPath bodyJsonPath = response.getBody().jsonPath();
        int id = bodyJsonPath.getInt("id");
        String username = bodyJsonPath.getString("username");
        String email = bodyJsonPath.getString("email");
        assertEquals(Constants.USER.getUserId(), id);
        assertEquals(Constants.USER.getUsername(), username);
        assertEquals(Constants.USER.getEmail(), email);
    }

    @Test
    @Order(2)
    public void getProfilePostsTest() {
        // Requires authentication
        authenticate();

        // Create a post for the user
        if (postId == -1) {
            postId = createPost();
            postCreated = true;
        }

        Response response = api.getProfilePosts(Constants.USER.getUserId(), Constants.PAGE);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        JsonPath bodyJsonPath = response.getBody().jsonPath();
        ArrayList<Object> posts = bodyJsonPath.get();
        assertTrue(posts.size() > 0);
    }

    @Test
    @Order(2)
    public void upgradeUserExpertiseTest() {
        // Requires authentication
        authenticate();

        String skill1 = getRandomSkill();
        String skill2 = getRandomSkill();
        String[] skills = new String[] { getRandomSkill(), getRandomSkill() };
        ExpertiseModel model = new ExpertiseModel(Constants.AVAILABILITY,
                Constants.CATEGORY_ALL,
                Constants.USER.getUserId(),
                skill1,
                skill2,
                skills);
        Response response = api.upgradeExpertise(Constants.USER.getUserId(), model);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        // Response example:
        // {"id":74,"skills":[{"skillId":137,"skill":"Fast learner 859","category":{"id":100,"name":"All"}},{"skillId":138,"skill":"Work under pressure 57","category":{"id":100,"name":"All"}},{"skillId":139,"skill":"Confidence 777","category":{"id":100,"name":"All"}},{"skillId":140,"skill":"Technical savvy 407","category":{"id":100,"name":"All"}}],"category":{"id":100,"name":"All"},"availability":0.5}
        JsonPath bodyJsonPath = response.getBody().jsonPath();
        int id = bodyJsonPath.getInt("id");
        ArrayList<Object> userSkills = bodyJsonPath.get("skills");
        double availability = bodyJsonPath.getDouble("availability");

        assertEquals(Constants.USER.getUserId(), id);
        assertEquals(Constants.AVAILABILITY, availability);
        assertTrue(userSkills.size() >= 4);
    }

    @Test
    @Order(2)
    public void upgradeUserPersonalTest() {
        // Requires authentication
        authenticate();

        PersonalModel model = new PersonalModel();
        Date date = faker.date().birthday();
        String formattedDate = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.ofInstant(date.toInstant(),
                ZoneId.systemDefault()));
        model.setBirthYear(formattedDate);
        model.setFirstName(faker.name().firstName());
        model.setId(Constants.USER.getUserId());
        model.setLastName(faker.name().lastName());
        model.setLocation(Constants.LOCATION);
        model.setPersonalReview(faker.lorem().sentence(5));
        model.setPicture("");
        model.setPicturePrivacy(true);
        model.setSex(PersonalModel.SEX_FEMALE);
        Response response = api.upgradePersonal(Constants.USER.getUserId(), model);

        int statusCode = response.getStatusCode();
        assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");

        // Response example:
        // {"id":74,"firstName":"Terence","lastName":"Feil","sex":"FEMALE","location":{"id":68,"city":{"id":1,"city":"Sofia","country":{}}},"birthYear":"1991-11-30","personalReview":"Porro tempora non neque debitis exercitationem.","memberSince":"08/10/2023","picturePrivacy":true}
        JsonPath bodyJsonPath = response.getBody().jsonPath();
        int id = bodyJsonPath.getInt("id");
        String firstName = bodyJsonPath.getString("firstName");
        String lastName = bodyJsonPath.getString("lastName");
        String sex = bodyJsonPath.getString("sex");
        String birthYear = bodyJsonPath.getString("birthYear");
        String personalReview = bodyJsonPath.getString("personalReview");
        boolean picturePrivacy = bodyJsonPath.getBoolean("picturePrivacy");

        assertEquals(Constants.USER.getUserId(), id);
        assertEquals(model.getFirstName(), firstName);
        assertEquals(model.getLastName(), lastName);
        assertEquals(model.getSex(), sex);
        assertEquals(model.getBirthYear(), birthYear);
        assertEquals(model.getPersonalReview(), personalReview);
        assertEquals(model.isPicturePrivacy(), picturePrivacy);
    }
}
