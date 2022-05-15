package com.sinn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sinn.pojo.Blog;
import com.sinn.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/15
 */
@Controller
public class BlogDetailsController {

    @Autowired
    BlogService blogService;

    @RequestMapping("/details/{blogId}")
    public String toBlogDetail(@PathVariable("blogId") Long blogId){
        LambdaQueryWrapper<Blog> blogQw=new LambdaQueryWrapper<>();
        blogQw.eq(Blog::getId,blogId);
        Blog blog = blogService.getOne(blogQw);


        return "blog";
    }

}
