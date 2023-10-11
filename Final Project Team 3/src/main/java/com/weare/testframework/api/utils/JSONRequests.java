package com.weare.testframework.api.utils;

public class JSONRequests {
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
