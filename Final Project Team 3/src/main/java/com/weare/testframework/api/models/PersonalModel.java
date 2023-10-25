package com.weare.testframework.api.models;

public class PersonalModel {
    public static final String SEX_MALE = "MALE";
    public static final String SEX_FEMALE = "FEMALE";

    private String birthYear;
    private String firstName;
    private int id;
    private String lastName;
    private LocationModel location;
    private String memberSince;
    private String personalReview;
    private String picture;
    private boolean picturePrivacy;
    private String sex;

    // Getters and setters

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public String getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(String memberSince) {
        this.memberSince = memberSince;
    }

    public String getPersonalReview() {
        return personalReview;
    }

    public void setPersonalReview(String personalReview) {
        this.personalReview = personalReview;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isPicturePrivacy() {
        return picturePrivacy;
    }

    public void setPicturePrivacy(boolean picturePrivacy) {
        this.picturePrivacy = picturePrivacy;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
