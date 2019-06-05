package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.PostVo;

@Repository
public class PostDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(PostVo postVo) {
		sqlSession.insert("post.insert", postVo);
	}
	
	public List<PostVo> get(PostVo postVo){
		return sqlSession.selectList("post.getPostList", postVo);
	}
	
	public PostVo getOne(PostVo postVo) {
		return sqlSession.selectOne("post.getOne", postVo);
	}
	
	public Long getMinNum(Long categoryNo) {
		return sqlSession.selectOne("post.getMinNum", categoryNo);
	}
	
	public void deleteByCategoryNo(Long no) {
		sqlSession.delete("post.deleteByCategoryNo", no);
	}
}
