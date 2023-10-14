package com.weare.testframework.api.utils;

public class JSONRequests {
    public static final String USER_REGISTER = "{\n" +
            "  \"authorities\": [\n" +
            "    \"ROLE_USER\"\n" +
            "  ],\n" +
            "  \"category\": {\n" +
            "    \"id\": 102,\n" +
            "    \"name\": \"Actor\"\n" +
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

    public static final String SEND_REQUEST_BODY = "{\n" +
            "    \"id\": %d,\n" +
            "    \"username\": \"%s\"\n" +
            "}";

    public static final String SKILL_CREATE = "{\n" +
            "    \"category\": {\n" +
            "        \"id\": %d,\n" +
            "        \"name\": \"%s\"\n" +
            "    },\n" +
            "    \"skill\": \"%s\",\n" +
            "    \"skillId\": 0\n" +
            "}";

    public static final String PAGE = "{\n" +
            " \"index\": %d,\n" +
            "  \"next\": %b,\n" +
            "    \"searchParam1\": \"%s\"\n" +
            "    \"searchParam2\": \"%s\"\n" +
            "    \"size\": %d\n" +
            "}";
}
