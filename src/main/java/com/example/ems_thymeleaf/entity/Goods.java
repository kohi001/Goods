package com.example.ems_thymeleaf.entity;

import javax.validation.constraints.NotNull;

public class Goods {
    @NotNull(message = "商品番号を入力してください")
    private Integer goodsId;
    private Integer goodsCode;
    private Integer size;
    private Integer color;
    private Integer bigClassification;
    private String middleClassification;
    private String file;
    private String price;
    private String expenses;
    private String description;
    private Integer discount;
    private String producer;
    private String quantity;

    public Goods() {
    }

    public Goods(Integer goodsId, Integer goodsCode, Integer size, Integer color, Integer bigClassification, String middleClassification, String file, String price, String expenses, String description, Integer discount, String producer, String quantity) {
        this.goodsId = goodsId;
        this.goodsCode = goodsCode;
        this.size = size;
        this.color = color;
        this.bigClassification = bigClassification;
        this.middleClassification = middleClassification;
        this.file = file;
        this.price = price;
        this.expenses = expenses;
        this.description = description;
        this.discount = discount;
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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getBigClassification() {
        return bigClassification;
    }

    public void setBigClassification(Integer bigClassification) {
        this.bigClassification = bigClassification;
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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
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
