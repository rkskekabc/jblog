package com.cafe24.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.CategoryService;
import com.cafe24.jblog.service.PostService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping({"", "/{pathVar1}", "/{pathVar1}/{pathVar2}"})
	public String main(
			@PathVariable String id,
			@PathVariable Optional<Long> pathVar1, 
			@PathVariable Optional<Long> pathVar2,
			@RequestParam(value="result", required=false) String result,
			ModelMap modelMap
			) {
		BlogVo blogVo = blogService.getBlog(id);
		System.out.println("blogVo : " + blogVo);
		System.out.println("pathVar1 : " + pathVar1.isPresent());
		System.out.println("pathVar2 : " + pathVar2.isPresent());
		Long categoryNo = pathVar1.isPresent() ? pathVar1.get() : categoryService.getMinNum(id);
		Long postNo = pathVar2.isPresent() ? pathVar2.get() : postService.getMinNum(categoryNo);
		List<CategoryVo> categoryList = categoryService.getCategoryListMini(id);
		
		PostVo dummyPostVo = new PostVo();
		dummyPostVo.setBlogId(id);
		dummyPostVo.setCategoryNo(categoryNo);
		List<PostVo> postList = postService.getPostList(dummyPostVo);
		
		dummyPostVo = new PostVo();
		dummyPostVo.setBlogId(id);
		dummyPostVo.setNo(postNo);
		dummyPostVo.setCategoryNo(categoryNo);
		PostVo postVo = postService.getOne(dummyPostVo);
		
		modelMap.addAttribute("blogVo", blogVo);
		modelMap.addAttribute("categoryList", categoryList);
		modelMap.addAttribute("postList", postList);
		modelMap.addAttribute("postVo", postVo);
		modelMap.addAttribute("result", result);
		return "blog/blog-main";
	}
	
	@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
	public String adminBasic(@PathVariable String id, Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("tab", "basic");
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	public String adminBasic(@PathVariable String id, @ModelAttribute BlogVo blogVo, 
							@RequestParam(value="logo-file") MultipartFile multipartFile) {
		String url = blogService.updateLogo(multipartFile, id);
		blogVo.setLogo(url);
		blogService.updateBlog(blogVo);
		return "redirect:/" + id + "/admin/basic";
	}
	
	@RequestMapping(value="/admin/category", method=RequestMethod.GET)
	public String adminCategory(@PathVariable String id, Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		List<CategoryVo> categoryList = categoryService.getCategoryList(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("tab", "category");
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="/admin/category", method=RequestMethod.POST)
	public String adminCategory(@PathVariable String id, @ModelAttribute CategoryVo categoryVo) {
		categoryService.insert(categoryVo);
		return "redirect:/" + id + "/admin/category";
	}
	
	@RequestMapping("/admin/category/delete/{no}")
	public String adminDeleteCategory(@PathVariable String id, @PathVariable Long no) {
		postService.deleteAppend(no);
		categoryService.delete(no);
		return "redirect:/" + id + "/admin/category";
	}
	
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String adminWrite(@PathVariable String id, Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		List<CategoryVo> categoryList = categoryService.getCategoryListMini(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("tab", "write");
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite(@PathVariable String id, @ModelAttribute PostVo postVo) {
		postService.write(postVo);
		return "redirect:/" + id + "/admin/category";
	}
}
