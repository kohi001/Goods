package com.example.ems_thymeleaf.entity;

public class GoodsColor {
    private Integer number;
    private String goodsColor;

    public GoodsColor() {
    }

    public GoodsColor(Integer number, String goodsColor) {
        this.number = number;
        this.goodsColor = goodsColor;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }
}
