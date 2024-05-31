package com.product.productproj.vo;

import java.math.BigDecimal;
import java.util.List;

public class SkusVO {
    private String skuId;
    private BigDecimal salePrice;
    private Integer stockNum;
    private List<AttrVO> skuAttrs;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
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

    public List<AttrVO> getSkuAttrs() {
        return skuAttrs;
    }

    public void setSkuAttrs(List<AttrVO> skuAttrs) {
        this.skuAttrs = skuAttrs;
    }
}
