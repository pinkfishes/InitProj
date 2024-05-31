package com.product.productproj.vo;

import java.math.BigDecimal;
import java.util.List;

public class ProductVO {
    private String itemId;
    private String title;
    private String brand;
    private List<CatVO> cats;
    private List<AttrVO> attrs;
    private String status;
    private List<SkusVO> skus;
    private BigDecimal minPrice;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<CatVO> getCats() {
        return cats;
    }

    public void setCats(List<CatVO> cats) {
        this.cats = cats;
    }

    public List<AttrVO> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<AttrVO> attrs) {
        this.attrs = attrs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SkusVO> getSkus() {
        return skus;
    }

    public void setSkus(List<SkusVO> skus) {
        this.skus = skus;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }
}
