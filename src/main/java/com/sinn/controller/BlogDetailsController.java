package com.sinn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sinn.mapper.PictureMapper;
import com.sinn.mapper.UserMapper;
import com.sinn.pojo.Blog;
import com.sinn.pojo.Picture;
import com.sinn.pojo.User;
import com.sinn.pojo.Vo.BlogVo;
import com.sinn.service.BlogService;
import com.sinn.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/15
 */
@Controller
@Slf4j
public class BlogDetailsController {

    @Autowired
    BlogService blogService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PictureService pictureService;

    /**
     * 查看微博的详细页面
     * @param blogId
     * @param model
     * @return
     */
    @RequestMapping("/details/{blogId}")
    public String toBlogDetail(@PathVariable("blogId") Long blogId, Model model){
        LambdaQueryWrapper<Blog> blogQw=new LambdaQueryWrapper<>();
        blogQw.eq(Blog::getId,blogId);
        Blog blog = blogService.getOne(blogQw);
        blog.setViews(blog.getViews()+1);
        blogService.updateById(blog);
        BlogVo blogVo=new BlogVo();
        BeanUtils.copyProperties(blog,blogVo);
        User AuthorUser = userMapper.selectById(blogVo.getUserId());
        blogVo.setUserName(AuthorUser.getUserName());

        //查询拥有的图片
        LambdaQueryWrapper<Picture> pictureQw=new LambdaQueryWrapper<>();
        pictureQw.eq(Picture::getBlogId,blogVo.getId());
        List<Picture> pictureList = pictureService.list(pictureQw);
        blogVo.setPictures(pictureList);


        model.addAttribute("blogVo",blogVo);
        log.info("此时的blogVo为"+blogVo);
        return "blog";
    }

}
