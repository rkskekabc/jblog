package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostDao postDao;
	
	public void write(PostVo postVo) {
		postDao.insert(postVo);
	}
	
	public List<PostVo> getPostList(PostVo postVo){
		return postDao.get(postVo);
	}
	
	public PostVo getOne(PostVo postVo) {
		return postDao.getOne(postVo);
	}
	
	public Long getMinNum(Long categoryNo) {
		return postDao.getMinNum(categoryNo);
	}
	
	public void deleteAppend(Long no) {
		postDao.deleteByCategoryNo(no);
	}
}
