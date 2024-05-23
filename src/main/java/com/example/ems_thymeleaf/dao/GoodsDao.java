package com.example.ems_thymeleaf.dao;

import com.example.ems_thymeleaf.dto.GoodsClassificationDto;
import com.example.ems_thymeleaf.entity.BigClassification;
import com.example.ems_thymeleaf.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsDao {

    List<GoodsClassificationDto> search(@Param("goodsCode") Integer goodsCode,
                                        @Param("producer") String producer,
                                        @Param("bigClassification") String bigClassification,
                                        @Param("price") String price,
                                        @Param("color") List<Integer> color,
                                        @Param("size") List<Integer> size);

    List<GoodsClassificationDto> getGoodWithClassifications();

    Goods findById(Integer goodsId);

    void update(Goods goods);

    void save(Goods goods);

    void delete(Integer goodsId);

    Goods findByCodeSizeAndColor(@Param("goodsCode")Integer goodsCode,
                                 @Param("size")Integer size,
                                 @Param("color")Integer color);
}
