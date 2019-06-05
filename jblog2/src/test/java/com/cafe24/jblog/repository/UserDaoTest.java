package com.cafe24.jblog.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.jblog.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext.xml"})
public class UserDaoTest {
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testUserInsert() {
		UserVo userVo = new UserVo();
		userVo.setId("rkskekfk");
		userVo.setName("rkskekfk");
		userVo.setPassword("rkskekfk");
		
		userDao.insert(userVo);
	}
}
