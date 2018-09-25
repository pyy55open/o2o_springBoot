package com.csy.o2o.dao;

import java.util.List;

import com.csy.o2o.entity.Area;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AreaDao {
	/**
	 * 查詢區域
	 * @return
	 */
	List<Area> queryArea();
}
