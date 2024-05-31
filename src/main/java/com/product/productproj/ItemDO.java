package com.product.productproj;

import java.math.BigDecimal;

public class ItemDO {

    private Integer id;
    private String ItemId;
    private String status;
    private String brand;
    private String level1CatName;
    private String level2CatName;
    private String level3CatName;
    private String title;
    private String skuId;
    private String attrName1;
    private String attrValue1;
    private String attrName2;
    private String attrValue2;
    private String attrName3;
    private String attrValue3;
    private BigDecimal salePrice;
    private BigDecimal minPrice;
    private Integer stockNum;
    private String editTime;

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public String getAttrValue2() {
        return attrValue2;
    }

    public void setAttrValue2(String attrValue2) {
        this.attrValue2 = attrValue2;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLevel1CatName() {
        return level1CatName;
    }

    public void setLevel1CatName(String level1CatName) {
        this.level1CatName = level1CatName;
    }

    public String getLevel2CatName() {
        return level2CatName;
    }

    public void setLevel2CatName(String level2CatName) {
        this.level2CatName = level2CatName;
    }

    public String getLevel3CatName() {
        return level3CatName;
    }

    public void setLevel3CatName(String level3CatName) {
        this.level3CatName = level3CatName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAttrName1() {
        return attrName1;
    }

    public void setAttrName1(String attrName1) {
        this.attrName1 = attrName1;
    }

    public String getAttrValue1() {
        return attrValue1;
    }

    public void setAttrValue1(String attrValue1) {
        this.attrValue1 = attrValue1;
    }

    public String getAttrName2() {
        return attrName2;
    }

    public void setAttrName2(String attrName2) {
        this.attrName2 = attrName2;
    }

    public String getAttrValue3() {
        return attrValue3;
    }

    public void setAttrValue3(String attrValue3) {
        this.attrValue3 = attrValue3;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getAttrName3() {
        return attrName3;
    }

    public void setAttrName3(String attrName3) {
        this.attrName3 = attrName3;
    }
}
