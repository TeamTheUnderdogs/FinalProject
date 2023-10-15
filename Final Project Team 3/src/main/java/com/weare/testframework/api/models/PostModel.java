package com.weare.testframework.api.models;

public class PostModel {
    private String content;
    private String picture;
    private boolean isPublic;

    public PostModel(String content, String picture, boolean isPublic) {
        this.content = content;
        this.picture = picture;
        this.isPublic = isPublic;
    }

    public String getContent() {
        return content;
    }

    public String getPicture() {
        return picture;
    }

    public boolean isPublic() {
        return isPublic;
    }
}
