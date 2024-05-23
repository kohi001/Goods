package com.example.ems_thymeleaf.entity;

import javax.validation.constraints.NotNull;

public class Skill {
    @NotNull(message = "社員番号を入力してください")
    private Integer emplyee_id;
    private String language;
    private String data_base;
    private String qualification;
    private String framework;
    private String experience;
    private String OS;

    public Skill() {
    }

    public Skill(Integer emplyee_id, String language, String data_base, String qualification, String framework, String experience, String OS) {
        this.emplyee_id = emplyee_id;
        this.language = language;
        this.data_base = data_base;
        this.qualification = qualification;
        this.framework = framework;
        this.experience = experience;
        this.OS = OS;
    }

    public Integer getEmplyee_id() {
        return emplyee_id;
    }

    public void setEmplyee_id(Integer emplyee_id) {
        this.emplyee_id = emplyee_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getData_base() {
        return data_base;
    }

    public void setData_base(String data_base) {
        this.data_base = data_base;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }
}
