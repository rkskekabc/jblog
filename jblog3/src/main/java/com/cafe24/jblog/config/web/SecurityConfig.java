package com.cafe24.jblog.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cafe24.jblog.security.AuthAdminInterceptor;
import com.cafe24.jblog.security.AuthLoginInterceptor;
import com.cafe24.jblog.security.AuthLogoutInterceptor;

@Configuration
public class SecurityConfig extends WebMvcConfigurerAdapter {

	// Interceptor
	@Bean
	public AuthLoginInterceptor authLoginInterceptor() {
		return new AuthLoginInterceptor();
	}

	@Bean
	public AuthLogoutInterceptor authLogoutInterceptor() {
		return new AuthLogoutInterceptor();
	}

	@Bean
	public AuthAdminInterceptor authAdminInterceptor() {
		return new AuthAdminInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(authLoginInterceptor())
			.addPathPatterns("/user/auth");
		
		registry
			.addInterceptor(authLogoutInterceptor())
			.addPathPatterns("/user/logout");

		registry
			.addInterceptor(authAdminInterceptor())
			.addPathPatterns("/**/admin/*");
	}
	
}
