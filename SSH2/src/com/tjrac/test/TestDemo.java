package com.tjrac.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tjrac.beans.User;
import com.tjrac.service.UserService;

public class TestDemo {

	@Test
	public void demo(){
		User user = new User();
		user.setName("spring");
		user.setPassword("222333");
		
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserService userService = (UserService) ac.getBean("userService");
		
		userService.saveUser(user);
	}
}
