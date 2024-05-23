package com.example.ems_thymeleaf.entity;

public class GoodsDiscount {
    private Integer discountId;
    private String discountvalue;

    public GoodsDiscount() {
    }

    public GoodsDiscount(Integer discountId, String discountvalue) {
        this.discountId = discountId;
        this.discountvalue = discountvalue;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public String getDiscountvalue() {
        return discountvalue;
    }

    public void setDiscountvalue(String discountvalue) {
        this.discountvalue = discountvalue;
    }
}
