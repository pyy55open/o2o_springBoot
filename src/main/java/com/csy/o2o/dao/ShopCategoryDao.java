package com.csy.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.csy.o2o.entity.ShopCategory;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShopCategoryDao {

	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategory);
}
