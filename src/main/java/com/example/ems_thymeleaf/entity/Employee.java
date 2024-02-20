package com.example.ems_thymeleaf.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Employee {
    @NotNull(message = "社員番号を入力してください")
    private Integer emplyee_id;
    @NotEmpty(message = "社員の名前を入力してください")
    private String employee_name;
    private String employee_password;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth_date;
    private String address;
    private String mail_address;
    private Integer phone_number;
    private Integer department;
    private String job_title;
    private String employment_status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hire_date;

    private Date create_date;
    private LocalDate update_date;
    private Integer insurance_number;
    private Integer annuity_number;
    private Integer position;

    private String resume;

    public Employee() {
    }

    public Employee(Integer emplyee_id, String employee_name, String employee_password, String sex, LocalDate birth_date, String address, String mail_address, Integer phone_number, Integer department, String job_title, String employment_status, Date hire_date, Date create_date, LocalDate update_date, Integer insurance_number, Integer annuity_number, Integer position, String resume) {
        this.emplyee_id = emplyee_id;
        this.employee_name = employee_name;
        this.employee_password = employee_password;
        this.sex = sex;
        this.birth_date = birth_date;
        this.address = address;
        this.mail_address = mail_address;
        this.phone_number = phone_number;
        this.department = department;
        this.job_title = job_title;
        this.employment_status = employment_status;
        this.hire_date = hire_date;
        this.create_date = create_date;
        this.update_date = update_date;
        this.insurance_number = insurance_number;
        this.annuity_number = annuity_number;
        this.position = position;
        this.resume = resume;
    }

    public Integer getEmplyee_id() {
        return emplyee_id;
    }

    public void setEmplyee_id(Integer emplyee_id) {
        this.emplyee_id = emplyee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_password() {
        return employee_password;
    }

    public void setEmployee_password(String employee_password) {
        this.employee_password = employee_password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail_address() {
        return mail_address;
    }

    public void setMail_address(String mail_address) {
        this.mail_address = mail_address;
    }

    public Integer getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getEmployment_status() {
        return employment_status;
    }

    public void setEmployment_status(String employment_status) {
        this.employment_status = employment_status;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public LocalDate getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(LocalDate update_date) {
        this.update_date = update_date;
    }

    public Integer getInsurance_number() {
        return insurance_number;
    }

    public void setInsurance_number(Integer insurance_number) {
        this.insurance_number = insurance_number;
    }

    public Integer getAnnuity_number() {
        return annuity_number;
    }

    public void setAnnuity_number(Integer annuity_number) {
        this.annuity_number = annuity_number;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}










