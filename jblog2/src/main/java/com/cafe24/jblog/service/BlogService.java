package com.cafe24.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.vo.BlogVo;

@Service
public class BlogService {
	private static final String SAVE_PATH = "/jblog-uploads";
	private static final String URL = "/images";
	private static final String SAVE_FILE_NAME = "logo";
	
	@Autowired
	private BlogDao blogDao;
	
	public void updateBlog(BlogVo blogVo) {
		blogDao.update(blogVo);
	}
	
	public BlogVo getBlog(String id) {
		return blogDao.get(id);
	}
	
	public String updateLogo(MultipartFile multipartFile, String blogId) {
		String url = "";

		try {
		
			if(multipartFile.isEmpty()) {
				return url;
			}

			String originalFilename = multipartFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf('.')+1);
			
			byte[] fileData = multipartFile.getBytes();
			String folderPath = SAVE_PATH + "/" + blogId;
			File folder = new File(folderPath);
			if(!folder.exists()) {
				folder.mkdirs();
			}
			
			String filePath = folderPath + "/" + SAVE_FILE_NAME + "." + extName;
			
			OutputStream os = new FileOutputStream(filePath);
			os.write(fileData);
			os.close();
			
			url = URL + "/" + blogId + "/" + SAVE_FILE_NAME + "." + extName;
			
		} catch (IOException e) {
			throw new RuntimeException("Fileupload error:" + e);
		}
		
		return url;
	}
}
