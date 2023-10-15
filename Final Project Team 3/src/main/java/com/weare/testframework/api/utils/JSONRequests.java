package com.weare.testframework.api.utils;

public class JSONRequests {
    public static final String USER_REGISTER_BODY = "{\n" +
            "  \"authorities\": [\n" +
            "    \"%s\"\n" +
            "  ],\n" +
            "  \"category\": {\n" +
            "    \"id\": %d,\n" +
            "    \"name\": \"%s\"\n" +
            "  },\n" +
            "  \"confirmPassword\": \"%s\",\n" +
            "  \"email\": \"%s\",\n" +
            "  \"password\": \"%s\",\n" +
            "  \"username\": \"%s\"\n" +
            "}";
    public static final String POST_CREATE_UPDATE_BODY = "{\n" +
            "    \"content\": \"%s\",\n" +
            "    \"picture\": \"%s\",\n" +
            "    \"public\": %b\n" +
            "}";

    public static final String COMMENT_CREATE_UPDATE_BODY = "{\n" +
            "    \"content\": \"%s\",\n" +
            "    \"postId\": %d,\n" +
            "    \"userId\": %d\n" +
            "}";

    public static final String SEND_REQUEST_BODY = "{\n" +
            "    \"id\": %d,\n" +
            "    \"username\": \"%s\"\n" +
            "}";

    public static final String SKILL_CREATE_BODY = "{\n" +
            "    \"category\": {\n" +
            "        \"id\": %d,\n" +
            "        \"name\": \"%s\"\n" +
            "    },\n" +
            "    \"skill\": \"%s\",\n" +
            "    \"skillId\": 0\n" +
            "}";

    public static final String PAGE_BODY = "{\n" +
            " \"index\": %d,\n" +
            "  \"next\": %b,\n" +
            "    \"searchParam1\": \"%s\",\n" +
            "    \"searchParam2\": \"%s\",\n" +
            "    \"size\": %d\n" +
            "}";

    public static final String EXPERTISE_PROFILE_BODY = "{\n" +
            "  \"availability\": %.2f,\n" +
            "  \"category\": {\n" +
            "    \"id\": %d,\n" +
            "    \"name\": \"%s\"\n" +
            "  },\n" +
            "  \"id\": %d,\n" +
            "  \"skill1\": \"%s\",\n" +
            "  \"skill2\": \"%s\",\n" +
            "  \"skills\": [\n" +
            "    %s\n" +
            "  ]\n" +
            "}";

    public static final String PERSONAL_PROFILE_BODY = "{\n" +
            "  \"birthYear\": \"%s\",\n" +
            "  \"firstName\": \"%s\",\n" +
            "  \"id\": %d,\n" +
            "  \"lastName\": \"%s\",\n" +
            "  \"location\": {\n" +
            "    \"city\": {\n" +
            "      \"city\": \"%s\",\n" +
            "      \"country\": {},\n" +
            "      \"id\": %d \n" +
            "    }\n" +
            "  },\n" +
            "  \"personalReview\": \"%s\",\n" +
            "  \"picture\": \"%s\",\n" +
            "  \"picturePrivacy\": %b,\n" +
            "  \"sex\": \"%s\"\n" +
            "}";
}
