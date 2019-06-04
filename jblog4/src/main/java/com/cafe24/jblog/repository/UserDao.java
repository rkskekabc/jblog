package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	public String insert(UserVo userVo) {
		sqlSession.insert("user.insert", userVo);
		return userVo.getId();
	}
	
	public UserVo getById(String id) {
		return sqlSession.selectOne("user.getByEmail", id);
	}
	
	public UserVo get(UserVo userVo) {
		return sqlSession.selectOne("user.select", userVo);
	}
}
