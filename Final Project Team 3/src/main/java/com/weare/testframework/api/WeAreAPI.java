package com.weare.testframework.api;

import com.github.javafaker.Faker;
import com.weare.testframework.api.models.UserModel;
import com.weare.testframework.api.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Objects;

import static com.weare.testframework.Utils.getConfigPropertyByKey;

public class WeAreAPI {
    private static Cookies authenticateCookies;
    private static Cookies adminAuthenticateCookies;

    public static Faker faker = new Faker();

    public static boolean hasAuthenticateCookies() {
        return authenticateCookies != null;
    }

    public static boolean hasAdminAuthenticateCookies() {
        return adminAuthenticateCookies != null;
    }

    protected RequestSpecification getRestAssured() {
        return getRestAssured(false);
    }

    protected RequestSpecification getRestAssured(boolean authenticateAdmin) {
        if (authenticateAdmin && adminAuthenticateCookies != null) {
            return getRestAssured(adminAuthenticateCookies);
        }
        if (!authenticateAdmin && authenticateCookies != null) {
            return getRestAssured(authenticateCookies);
        }
        return getRestAssured(null);
    }

    protected RequestSpecification getRestAssured(Cookies cookies) {
        String baseUrl = getConfigPropertyByKey("social.api.apiUrl");
        RequestSpecification requestSpecification = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .baseUri(baseUrl);

        if (cookies != null) {
            requestSpecification.cookies(cookies);
        }
        return requestSpecification;
    }

    public static void authenticateAndFetchCookies() {
        authenticateAndFetchCookies(false);
    }

    public static void authenticateAndFetchCookies(boolean authenticateAdmin) {
        // Remove current cookies
        if (authenticateAdmin) {
            adminAuthenticateCookies = null;
        } else {
            authenticateCookies = null;
        }

        // Register new user if needed
        if (authenticateAdmin && Constants.ADMIN == null) {
            Constants.ADMIN = registerNewUser(Constants.ROLE_ADMIN);
        }

        if (!authenticateAdmin && Constants.USER == null) {
            Constants.USER = registerNewUser(Constants.ROLE_USER);
        }

        // Get the user to authenticate
        UserModel userModel;
        if (authenticateAdmin) {
            userModel = Constants.ADMIN;
        } else {
            userModel = Constants.USER;
        }

        // Authorize to get cookies
        Cookies cookies = authenticateAndFetchCookies(userModel.getUsername(), userModel.getPassword());
        if (cookies != null) {
            if (authenticateAdmin) {
                adminAuthenticateCookies = cookies;
            } else {
                authenticateCookies = cookies;
            }
        }
    }

    public static Cookies authenticateAndFetchCookies(String username, String password) {
        // Authorize to get cookies
        RestAssured.baseURI = getConfigPropertyByKey("social.api.baseUrl");

        Response response = RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("username", username)
                .multiPart("password", password)
                .when()
                .post("authenticate");

        Cookies cookies = response.detailedCookies();
        System.out.printf("The authentication for user: %s, is with status code: %s.%n",
                username, response.getStatusCode());
        // The JSESSIONID cookie is the auth one
        if (cookies.get("JSESSIONID") != null) {
            return cookies;
        }
        return null;
    }

    public static UserModel registerNewUser(String userRole) {
        UsersAPI api = new UsersAPI();
        String password = faker.internet().password(8, 12);
        String email = faker.internet().emailAddress();
        String username = faker.name().firstName();

        if (Objects.equals(userRole, Constants.ROLE_ADMIN)) {
            username = String.format("%s%s", Constants.ADMIN_PREFIX, username);
            password = String.format("%s%s", Constants.ADMIN_PREFIX, password);
        }

        Response response = api.registerUser(userRole,
                Constants.CATEGORY_ALL,
                password, email, password, username);

        // Response example:
        // User with name Buddy and id 86 was created
        String responseBody = response.getBody().print();
        String[] splitByIntervals = responseBody.split(" ");
        String usernameActual = splitByIntervals[Constants.USERNAME_INDEX];
        int userId = Integer.parseInt(splitByIntervals[Constants.USER_ID_INDEX]);

        return new UserModel(userId, usernameActual, password, email);
    }

    public static String arrayToJSONString(String[] array) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            String element = array[i];
            if (i < array.length - 1) {
                builder.append(String.format("\"%s\",\n", element));
            } else {
                builder.append(String.format("\"%s\"", element));
            }
        }
        return builder.toString();
    }
}