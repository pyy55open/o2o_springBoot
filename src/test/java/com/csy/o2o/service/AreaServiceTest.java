package com.csy.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import com.csy.o2o.entity.Area;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaServiceTest{

	@Autowired
	AreaService areaService;
	
	@Test
	public void testGetArea(){
		List<Area> list = areaService.getArea();
		assertEquals("问",list.get(0).getAreaname());
	}
}
