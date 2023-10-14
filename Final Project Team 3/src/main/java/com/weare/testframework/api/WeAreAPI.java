package com.weare.testframework.api;

import com.github.javafaker.Faker;
import com.weare.testframework.api.utils.Constants;
import com.weare.testframework.api.utils.JSONRequests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Cookies;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.weare.testframework.Utils.getConfigPropertyByKey;

public class WeAreAPI {
    private static Cookies authenticateCookies;
    public static Faker faker = new Faker();

    public static boolean hasAuthenticateCookies() {
        return authenticateCookies != null;
    }

    protected RequestSpecification getRestAssured() {
        String baseUrl = getConfigPropertyByKey("social.api.apiUrl");
        RequestSpecification requestSpecification = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .baseUri(baseUrl);

        if (authenticateCookies != null) {
            requestSpecification.cookies(authenticateCookies);
        }
        return requestSpecification;
    }

    public static void authenticateAndFetchCookies() {
        // Remove current cookies
        authenticateCookies = null;
        //Constants.USER_ID = -1;

        // Authorize to get cookies
        RestAssured.baseURI = getConfigPropertyByKey("social.api.baseUrl");
        String username = getConfigPropertyByKey("social.api.username");
        String password = getConfigPropertyByKey("social.api.password");

        Response response = RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("username", username)
                .multiPart("password", password)
                .when()
                .post("authenticate");

        Cookies cookies = response.detailedCookies();
        // The JSESSIONID cookie is the auth one
        if (cookies.get("JSESSIONID") != null) {
            authenticateCookies = cookies;
        }
        int statusCodeAuthentication = response.getStatusCode();
        System.out.println("The status code is:" + statusCodeAuthentication);

        // Extract user id from the authenticate html response
//        String pattern = "/auth/users/(\\d+)/profile";
//        Pattern regex = Pattern.compile(pattern);
//        Matcher matcher = regex.matcher(response.getBody().toString());
//        boolean userIdFound = matcher.find();
//
//        if (userIdFound) {
//            Constants.USER_ID = Integer.parseInt(matcher.group(1));
//        }
//        System.out.println("The user id is:" + Constants.USER_ID);


    }
    public String getRandomUsername() {
        return faker.name().firstName();
    }

    public String getRandomPassword() {
        return faker.internet().emailAddress();
    }

    public String getRandomEmail() {
        return faker.internet().emailAddress();
    }
}