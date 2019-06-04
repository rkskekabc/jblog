package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(BlogVo blogVo) {
		sqlSession.insert("blog.insert", blogVo);
	}
	
	public void update(BlogVo blogVo) {
		sqlSession.update("blog.update", blogVo);
	}
	
	public BlogVo get(String id) {
		return sqlSession.selectOne("blog.select", id);
	}
}
