package com.tjrac.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tjrac.beans.User;
import com.tjrac.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class SpringTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void demo2(){
		User user = new User();
		user.setName("spring");
		user.setPassword("222333");
		
		userService.saveUser(user);
	}
}
