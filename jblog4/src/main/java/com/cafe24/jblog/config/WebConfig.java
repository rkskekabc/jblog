package com.cafe24.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cafe24.jblog.config.web.FileuploadConfig;
import com.cafe24.jblog.config.web.MVCConfig;
import com.cafe24.jblog.config.web.MessageConfig;
import com.cafe24.jblog.config.web.SecurityConfig;


@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.jblog.controller"})
@Import({MVCConfig.class, SecurityConfig.class, MessageConfig.class, FileuploadConfig.class})
public class WebConfig {

}
