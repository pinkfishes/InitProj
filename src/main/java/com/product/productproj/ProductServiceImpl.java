package com.product.productproj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.product.productproj.mapper.ItemDao;
import com.product.productproj.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private static Cache<String, String> gCache = CacheBuilder.newBuilder().concurrencyLevel(4)
            .expireAfterWrite(5, TimeUnit.MINUTES).maximumSize(1000).build();


    @Autowired
    private ItemDao itemDAO;
    //使用
    @Autowired
    private Cache<String,Object> guavaCache;


    @Override
    public void query() {

        ItemDO item = itemDAO.selectByPrimaryKey(1);
        System.out.println(item.getBrand());

    }

    @Override
    public ResultVO productList(List<String> itemIds) {
        ResultVO resultVO=new ResultVO();
        List<ItemDO> itemDOList=itemDAO.list(itemIds);
        if(CollectionUtils.isEmpty(itemDOList)){
            return resultVO;
        }

        //属性信息:先从cache取，不存在查db
        List<ItemDO> attrList=new ArrayList<>();
        Map<String, List<ItemDO>> catMap = new HashMap<>();
        String key=JSON.toJSONString(itemIds);
        String attrCache = gCache.getIfPresent(key);
        if(attrCache==null){
            attrList = itemDAO.attrList(itemDOList.stream().map(ItemDO::getItemId).collect(Collectors.toList()));
            if(!CollectionUtils.isEmpty(attrList)){
                catMap = attrList.stream().collect(Collectors.groupingBy(ItemDO::getItemId));
                gCache.put(key,JSON.toJSONString(catMap));
            }
        }else{
            catMap= JSON.parseObject(attrCache,new TypeReference<Map<String, List<ItemDO>>>(){});
        }

        //组装数据
        List<ProductVO> productList=new ArrayList<>();
        for (ItemDO itemDO : itemDOList) {
            ProductVO productVO=new ProductVO();
            productVO.setItemId(itemDO.getItemId());
            productVO.setTitle(itemDO.getTitle());
            productVO.setBrand(itemDO.getBrand());
            productVO.setStatus(itemDO.getStatus());
            productVO.setMinPrice(itemDO.getMinPrice());

            //填充其他属性
//            productVO.setCats(convertCat(catMap.get(itemDO.getItemId())));
            List<CatVO> catVOList=new ArrayList<>();
            catVOList.add(convertCat(itemDO));
            productVO.setCats(catVOList);
            productVO.setAttrs(convertAttrList(catMap.get(itemDO.getItemId())));
            productVO.setSkus(convertSku(catMap.get(itemDO.getItemId())));
            productList.add(productVO);
        }

        resultVO.setProductList(productList);
        return resultVO;
    }

    private List<SkusVO> convertSku(List<ItemDO> itemDOList){
        if(CollectionUtils.isEmpty(itemDOList)){
            return new ArrayList<>();
        }
        List<SkusVO> skusVOList=new ArrayList<>();
        for (ItemDO itemDO : itemDOList) {
            SkusVO skusVO=new SkusVO();
            skusVO.setSkuId(itemDO.getSkuId());
            skusVO.setStockNum(itemDO.getStockNum());
            skusVO.setSalePrice(itemDO.getSalePrice());
            skusVO.setSkuAttrs(convertAttr(itemDO));
            skusVOList.add(skusVO);
        }
        return skusVOList;
    }

    private List<AttrVO> convertAttr(ItemDO itemDO){
        if(itemDO==null){
            return new ArrayList<>();
        }
        List<AttrVO> attrVOList=new ArrayList<>();
        if(!StringUtils.isEmpty(itemDO.getAttrName1())){
            AttrVO attrVO1=new AttrVO();
            attrVO1.setAttrKey(itemDO.getAttrName1());
            attrVO1.setAttrValue(itemDO.getAttrValue1());
            attrVOList.add(attrVO1);
        }

        if(!StringUtils.isEmpty(itemDO.getAttrName2())){
            AttrVO attrVO2=new AttrVO();
            attrVO2.setAttrKey(itemDO.getAttrName2());
            attrVO2.setAttrValue(itemDO.getAttrValue2());
            attrVOList.add(attrVO2);
        }

        if(!StringUtils.isEmpty(itemDO.getAttrName3())){
            AttrVO attrVO3=new AttrVO();
            attrVO3.setAttrKey(itemDO.getAttrName3());
            attrVO3.setAttrValue(itemDO.getAttrValue3());
            attrVOList.add(attrVO3);
        }
        return attrVOList;
    }

    private List<AttrVO> convertAttrList(List<ItemDO> itemDOList){
        if(CollectionUtils.isEmpty(itemDOList)){
            return new ArrayList<>();
        }
        List<AttrVO> attrVOList=new ArrayList<>();
        for (ItemDO itemDO : itemDOList) {
            List<AttrVO> convert = convertAttr(itemDO);
            attrVOList.addAll(convert);
        }
        return attrVOList;
    }


    private CatVO convertCat(ItemDO itemDO){
        CatVO catVO=new CatVO();
        if(itemDO!=null){
            catVO.setLevel1CatName(itemDO.getLevel1CatName());
            catVO.setLevel2CatName(itemDO.getLevel2CatName());
            catVO.setLevel3CatName(itemDO.getLevel3CatName());
        }
        return catVO;
    }

//    private List<CatVO> convertCat(List<ItemDO> itemDOList){
//        if(CollectionUtils.isEmpty(itemDOList)){
//            return new ArrayList<>();
//        }
//        List<CatVO> catVOList=new ArrayList<>();
//        for (ItemDO itemDO : itemDOList) {
//            CatVO catVO=new CatVO();
//            catVO.setLevel1CatName(itemDO.getLevel1CatName());
//            catVO.setLevel2CatName(itemDO.getLevel2CatName());
//            catVO.setLevel3CatName(itemDO.getLevel3CatName());
//            catVOList.add(catVO);
//        }
//        return catVOList;
//    }
}
