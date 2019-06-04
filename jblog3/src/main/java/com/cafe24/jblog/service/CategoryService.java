package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	public void insert(CategoryVo categoryVo) {
		categoryDao.insert(categoryVo);
	}
	
	public List<CategoryVo> getCategoryList(String blogId){
		return categoryDao.getByBlogId(blogId);
	}
	
	public List<CategoryVo> getCategoryListMini(String blogId){
		return categoryDao.getMini(blogId);
	}
	
	public Long getMinNum(String blogId) {
		return categoryDao.getMinNum(blogId);
	}
	
	public void delete(Long no) {
		categoryDao.delete(no);
	}
}
