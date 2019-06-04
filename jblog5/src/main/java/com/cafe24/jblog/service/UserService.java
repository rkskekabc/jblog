package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.UserVo;

@Service
public class UserService {
	private final String DEFAULT_TITLE="기본 타이틀";
	private final String DEFAULT_LOGO="/assets/images/spring-logo.jpg";
	private final String DEFAULT_CATEGORY="기본 카테고리";
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	public void join(UserVo userVo) {
		userDao.insert(userVo);
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setTitle(DEFAULT_TITLE);
		blogVo.setLogo(DEFAULT_LOGO);
		blogDao.insert(blogVo);
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName(DEFAULT_CATEGORY);
		categoryVo.setDescription(DEFAULT_CATEGORY);
		categoryVo.setBlogId(blogVo.getId());
		categoryDao.insert(categoryVo);
	}
	
	public UserVo getUser(UserVo userVo) {
		return userDao.get(userVo);
	}
	
	public boolean existId(String id) {
		return userDao.getById(id) != null;
	}
}
