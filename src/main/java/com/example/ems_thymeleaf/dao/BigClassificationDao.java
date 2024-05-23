package com.example.ems_thymeleaf.dao;

import com.example.ems_thymeleaf.entity.BigClassification;

import java.util.List;

public interface BigClassificationDao {

    List<BigClassification> findAll();
}
