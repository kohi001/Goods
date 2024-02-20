package com.example.ems_thymeleaf.dao;

import com.example.ems_thymeleaf.entity.Position;

import java.util.List;

public interface PositionDao {

    List<Position> findAll();
}
