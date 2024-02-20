package com.example.ems_thymeleaf.dao;

import com.example.ems_thymeleaf.entity.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> findAll();
}
