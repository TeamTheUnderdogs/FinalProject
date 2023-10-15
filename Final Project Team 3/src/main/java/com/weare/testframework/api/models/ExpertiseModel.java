package com.weare.testframework.api.models;

public class ExpertiseModel {
    private double availability;
    private CategoryModel category;
    private int id;
    private String skill1;
    private String skill2;
    private String skill3;
    private String skill4;
    private String skill5;
    private String[] skills;

    public ExpertiseModel(double availability, CategoryModel categoryModel,
                          int id, String skill1, String skill2, String[] skills) {
        this(availability, categoryModel, id, skill1, skill2, "", "", "", skills);
    }

    public ExpertiseModel(double availability, CategoryModel categoryModel,
                          int id, String skill1, String skill2,
                          String skill3, String skill4, String skill5, String[] skills) {
        this.availability = availability;
        this.id = id;
        this.category = categoryModel;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.skill4 = skill4;
        this.skill5 = skill5;
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public double getAvailability() {
        return availability;
    }

    public String getSkill1() {
        return skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public String getSkill4() {
        return skill4;
    }

    public String getSkill5() {
        return skill5;
    }

    public String[] getSkills() {
        return skills;
    }
}
