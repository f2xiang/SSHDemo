package com.tjrac.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.tjrac.beans.User;

public class TestDemo {

	@Test
	public void demo(){
		User user = new User();
		user.setName("уехЩ");
		user.setPassword("111");
		
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
		factory.close();
	}
}
