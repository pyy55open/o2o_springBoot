package com.csy.o2o.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.csy.o2o.dto.LocalAuthExcution;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalAuthServiceTest{

	@Autowired
	LocalAuthService localAuthService;
	
	@Test
	public void testUpdatePwd(){
		LocalAuthExcution l =localAuthService.updatePwd(1L, "老王", "123456", "csy665789987", new Date());
		System.out.println(l.getStateInfo());
	}
}