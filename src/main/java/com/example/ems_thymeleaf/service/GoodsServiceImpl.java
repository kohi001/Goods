package com.example.ems_thymeleaf.service;

import com.example.ems_thymeleaf.dao.*;
import com.example.ems_thymeleaf.dto.GoodsClassificationDto;
import com.example.ems_thymeleaf.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService{
    private GoodsDao goodsDao;
    private BigClassificationDao bigClassificationDao;
    private GoodsColorDao goodsColorDao;
    private GoodsDiscountDao goodsDiscountDao;
    private SizeDao sizeDao;
    @Autowired
    public GoodsServiceImpl(GoodsDao goodsDao,BigClassificationDao bigClassificationDao,GoodsColorDao goodsColorDao,GoodsDiscountDao goodsDiscountDao,SizeDao sizeDao) {
       this.bigClassificationDao = bigClassificationDao;
       this.goodsDao = goodsDao;
       this.goodsColorDao = goodsColorDao;
       this.goodsDiscountDao = goodsDiscountDao;
       this.sizeDao = sizeDao;
    }

    @Override
    public List<GoodsClassificationDto> search(Integer goodsCode, String producer, String bigClassification, String price,List<Integer> color,List<Integer> size) {
        return goodsDao.search(goodsCode,producer,bigClassification,price,color,size);
    }

    @Override
    public List<GoodsClassificationDto> getGoodWithClassifications() {
        return goodsDao.getGoodWithClassifications();
    }

    @Override
    public Goods findById(Integer goodsId) {
        return goodsDao.findById(goodsId);
    }

    @Override
    public void update(Goods goods) {
        goodsDao.update(goods);
    }

    @Override
    public List<BigClassification> findBigClassification() {
        return bigClassificationDao.findAll();
    }


    @Override
    public boolean isGoodsValid(Integer goodsCode,Integer size,Integer color) {
        Goods existgood = goodsDao.findByCodeSizeAndColor(goodsCode, size, color);
        return existgood == null;
    }

    @Override
    public void save(Goods goods) {
        goodsDao.save(goods);

    }

    @Override
    public void delete(Integer goodsId) {
        goodsDao.delete(goodsId);
    }

    @Override
    public List<GoodsColor> findColor() {
        return goodsColorDao.findAll();
    }

    @Override
    public List<GoodsDiscount> findDiscount() {
        return goodsDiscountDao.findAll();
    }

    @Override
    public List<Size> findSize() {
        return sizeDao.findAll();
    }
}
