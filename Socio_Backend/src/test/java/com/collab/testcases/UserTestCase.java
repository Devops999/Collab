/*
package com.collab.testcases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.collab.dao.UserDao;
import com.collab.domain.User;

public class UserTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	@Autowired
	UserDao userDao;
	@Autowired
	User user;

	@Before
	public void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.collab.*");
		context.refresh();

		// get the userDao from context
		// get the user from context

		userDao = (UserDao) context.getBean("userDaoImpl");
		System.out.println(userDao);

		user = context.getBean(User.class);
		System.out.println(user);

	}

	// insert database

	@Test
	public void createUserTestCase() {
		user.setFirstName("palvi");
		user.setLastName("thakur");
		user.setPassword("palvi");
		user.setEmailId("palvi@gmail");
		user.setRole("ROLE_USER");
		user.setStatus("NA");
		user.setIsOnline("NA");
		boolean flag = userDao.save(user);
		System.out.println("saved");
		assertEquals("userTestCase", true, flag);

	}

}
*/