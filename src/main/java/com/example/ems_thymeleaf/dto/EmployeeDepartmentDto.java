package com.example.ems_thymeleaf.dto;

public class EmployeeDepartmentDto {
    Integer emplyee_id;
    String employee_name;
    String department_name;
    String address;
    String sex;

    public EmployeeDepartmentDto() {
    }

    public EmployeeDepartmentDto(Integer emplyee_id, String employee_name, String department_name, String address, String sex) {
        this.emplyee_id = emplyee_id;
        this.employee_name = employee_name;
        this.department_name = department_name;
        this.address = address;
        this.sex = sex;
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

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
