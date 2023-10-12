package com.weare.testframework.api.utils;

public class JSONRequests {
    public static final String CREATE_USER_BODY = "{\n" +
            "  \"authorities\": [\n" +
            "    \"ROLE_USER\"\n" +
            "  ],\n" +
            "  \"category\": {\n" +
            "    \"id\": 101,\n" +
            "    \"name\": \"Accountant\"\n" +
            "  },\n" +
            "  \"confirmPassword\": \"%s\",\n" +
            "  \"email\": \"%s\",\n" +
            "  \"password\": \"%s\",\n" +
            "  \"username\": \"%s\"\n" +
            "}";
    public static final String POST_CREATE_UPDATE = "{\n" +
            "    \"content\": \"%s\",\n" +
            "    \"picture\": \"%s\",\n" +
            "    \"public\": %b\n" +
            "}";

    public static final String COMMENT_CREATE_UPDATE = "{\n" +
            "    \"content\": \"%s\",\n" +
            "    \"postId\": %d,\n" +
            "    \"userId\": %d\n" +
            "}";

}
