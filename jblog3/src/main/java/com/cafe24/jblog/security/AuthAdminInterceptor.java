package com.cafe24.jblog.security;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.vo.UserVo;

public class AuthAdminInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("admin interceptor");
		Map<String, String> pathVariables = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		HttpSession session = request.getSession();
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			System.out.println("authUser null");
			response.sendRedirect(request.getContextPath() + "/" + pathVariables.get("id") + "?result=fail");
			return false;
		} else if(!authUser.getId().equals(pathVariables.get("id"))) {
			response.sendRedirect(request.getContextPath() + "/" + pathVariables.get("id") + "?result=fail");
			return false;
		}
		
		return true;
	}
}
