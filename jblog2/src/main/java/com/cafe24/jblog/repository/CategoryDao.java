package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(CategoryVo categoryVo) {
		sqlSession.insert("category.insert", categoryVo);
	}
	
	public List<CategoryVo> getByBlogId(String blogId){
		return sqlSession.selectList("category.selectByBlogId", blogId);
	}
	
	public List<CategoryVo> getMini(String blogId){
		return sqlSession.selectList("category.selectMini", blogId);
	}
	
	public Long getMinNum(String blogId) {
		return sqlSession.selectOne("category.getMinNum", blogId);
	}
	
	public void delete(Long no) {
		sqlSession.delete("category.delete", no);
	}
}
