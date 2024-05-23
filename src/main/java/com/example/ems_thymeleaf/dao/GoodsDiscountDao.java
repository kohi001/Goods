package com.example.ems_thymeleaf.dao;

import com.example.ems_thymeleaf.entity.GoodsDiscount;

import java.util.List;

public interface GoodsDiscountDao {
    List<GoodsDiscount> findAll();
}
