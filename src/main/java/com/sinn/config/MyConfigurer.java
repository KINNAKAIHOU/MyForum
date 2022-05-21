package com.sinn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/15
 */
@Configuration
public class MyConfigurer implements WebMvcConfigurer {
    @Value("${spring.servlet.multipart.location}")
    private String uploadRootPath; //注入路径

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler是指你设置的虚拟路径，前端展示页面时填入这个路径来进行访问
        //addResourceLocations是指实际的本地路径，你需要代理给虚拟路径来访问的路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + uploadRootPath);
    }
}
