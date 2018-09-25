package com.csy.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.csy.o2o.entity.HeadLine;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface HeadLineDao {

	/**
	 * 查询头条滚动图
	 * @param headLine
	 * @return
	 */
	List<HeadLine> queryHeadLine(@Param("HeadLineCondition") HeadLine headLine);
}
