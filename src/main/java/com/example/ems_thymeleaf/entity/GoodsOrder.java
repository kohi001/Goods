package com.example.ems_thymeleaf.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class GoodsOrder {
    private Integer orderId;
    private Integer goodsId;
    private String orderPerson;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;
    private Integer orderNumber;
    private Integer orderMoney;

    public GoodsOrder() {
    }

    public GoodsOrder(Integer orderId, Integer goodsId, String orderPerson, LocalDateTime orderDate, Integer orderNumber, Integer orderMoney) {
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.orderPerson = orderPerson;
        this.orderDate = orderDate;
        this.orderNumber = orderNumber;
        this.orderMoney = orderMoney;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getOrderPerson() {
        return orderPerson;
    }

    public void setOrderPerson(String orderPerson) {
        this.orderPerson = orderPerson;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Integer orderMoney) {
        this.orderMoney = orderMoney;
    }
}
