package com.example.ems_thymeleaf.dao;

import com.example.ems_thymeleaf.entity.Size;

import java.util.List;

public interface SizeDao {
    List<Size> findAll();
}
