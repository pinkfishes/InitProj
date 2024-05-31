package com.product.productproj.mapper;

import com.product.productproj.ItemDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao {

    List<ItemDO> list(@Param("itemIds") List<String> itemIds);

    List<ItemDO> attrList(@Param("itemIds") List<String> itemIds);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemDO record);

    int insertSelective(ItemDO record);

    ItemDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemDO record);

    int updateByPrimaryKey(ItemDO record);
}