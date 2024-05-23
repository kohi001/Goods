package com.example.ems_thymeleaf.service;

import com.example.ems_thymeleaf.dto.EmployeeDepartmentDto;
import com.example.ems_thymeleaf.dto.GoodsClassificationDto;
import com.example.ems_thymeleaf.entity.*;

import java.util.List;

public interface GoodsService {

    List<GoodsClassificationDto> search(Integer goodsCode, String producer, String bigClassification, String price,List<Integer> color,List<Integer> size);

    List<GoodsClassificationDto> getGoodWithClassifications();

    Goods findById(Integer goodsId);

    void update(Goods goods);

    List<BigClassification> findBigClassification();

    boolean isGoodsValid(Integer goodsCode,Integer size,Integer color);

    void save(Goods goods);

    void delete(Integer goodsId);

    List<GoodsColor> findColor();

    List<GoodsDiscount> findDiscount();

    List<Size> findSize();
}
