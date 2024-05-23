package com.example.ems_thymeleaf.dto;

public class GoodsClassificationDto {
    Integer goodsId;
    Integer goodsCode;
    String size_name;
    String goodsColor_name;
    String bigClassification_name;
    String middleClassification;
    String file;
    String price;
    String expenses;
    String description;
    String discount_value;
    String producer;
    String quantity;

    public GoodsClassificationDto() {
    }

    public GoodsClassificationDto(Integer goodsId, Integer goodsCode, String size_name, String goodsColor_name, String bigClassification_name, String middleClassification, String file, String price, String expenses, String description, String discount_value, String producer, String quantity) {
        this.goodsId = goodsId;
        this.goodsCode = goodsCode;
        this.size_name = size_name;
        this.goodsColor_name = goodsColor_name;
        this.bigClassification_name = bigClassification_name;
        this.middleClassification = middleClassification;
        this.file = file;
        this.price = price;
        this.expenses = expenses;
        this.description = description;
        this.discount_value = discount_value;
        this.producer = producer;
        this.quantity = quantity;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(Integer goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getSize_name() {
        return size_name;
    }

    public void setSize_name(String size_name) {
        this.size_name = size_name;
    }

    public String getGoodsColor_name() {
        return goodsColor_name;
    }

    public void setGoodsColor_name(String goodsColor_name) {
        this.goodsColor_name = goodsColor_name;
    }

    public String getBigClassification_name() {
        return bigClassification_name;
    }

    public void setBigClassification_name(String bigClassification_name) {
        this.bigClassification_name = bigClassification_name;
    }

    public String getMiddleClassification() {
        return middleClassification;
    }

    public void setMiddleClassification(String middleClassification) {
        this.middleClassification = middleClassification;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(String discount_value) {
        this.discount_value = discount_value;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
