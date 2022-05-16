package com.sinn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sinn.mapper.UserMapper;
import com.sinn.mapper.UserRoleRelationMapper;
import com.sinn.pojo.Blog;
import com.sinn.pojo.Picture;
import com.sinn.pojo.User;
import com.sinn.pojo.UserRoleRelation;
import com.sinn.pojo.Vo.BlogVo;
import com.sinn.service.BlogService;
import com.sinn.service.PictureService;
import com.sinn.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/12
 */
@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    BlogService blogService;

    @Autowired
    PictureService pictureService;

    @Autowired
    UserRoleRelationMapper userRoleRelationMapper;
    /**
     * 直接进入首页
     * 判断是否已经登陆，未登录则直接进入首页
     * 已登陆的情况下，将User对象放入Session中
     * 加入已存在的微博渲染
     * @return index
     */
    @RequestMapping({"/","/index"})
    public String index(Model model, Authentication auth, HttpSession session) {
        UserDetails userDetails = null;
        if (auth != null) {
            userDetails = (UserDetails) auth.getPrincipal();
            LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUserName,userDetails.getUsername());
            User user = userService.getOne(queryWrapper);
            session.setAttribute("user",user);
        }
        //渲染微博
        Set<Long> enableUserIds = getEnableUserIds();

        LambdaQueryWrapper<Blog> blogQw=new LambdaQueryWrapper<>();
        blogQw.eq(Blog::isShareStatement,true)     //是分享状态
                 .eq(Blog::isSeeAble,true)        //是可见状态
                .in(Blog::getUserId,enableUserIds)    //用户是可用状态
                .orderByDesc(Blog::getUpdateTime);
        List<Blog> blogList = blogService.list(blogQw);
            //获取其他信息封装成Vo
        List<BlogVo> blogVos=new LinkedList<>();
        for(Blog eachBlog: blogList){
            BlogVo blogVo = new BlogVo();
            BeanUtils.copyProperties(eachBlog,blogVo);
            //1.封装用户名信息
            User AuthorUser = userMapper.selectById(blogVo.getUserId());
            blogVo.setUserName(AuthorUser.getUserName());
            //2.封装图片信息
            LambdaQueryWrapper<Picture> pictureQw=new LambdaQueryWrapper<>();
            pictureQw.eq(Picture::getBlogId,blogVo.getId());
            List<Picture> pictureList = pictureService.list(pictureQw);
            blogVo.setPictures(pictureList);


            //最后添加
            blogVos.add(blogVo);
        }


        //侧边栏最新发布微博
        LambdaQueryWrapper<Blog> blogQw2=new LambdaQueryWrapper<>();
        blogQw2.in(Blog::getUserId,enableUserIds)
                .orderByDesc(Blog::getUpdateTime);
        List<Blog> newestBlogs = blogService.list(blogQw2);

        //点赞排行耪


        model.addAttribute("blogVoList",blogVos);
        model.addAttribute("newestBlogs",newestBlogs);
        model.addAttribute("userDetails", userDetails);
        return "index";
    }

    /**
     * 前往登陆页面
     * @return login
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }


    /**
     * 前往注册界面
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    /**
     * 注册用户，只要求输入用户名和密码，用户名是唯一的
     * 通过MP字段自动注入完成注册日期，并且添加授权
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public String register(String username,String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUserName(username);
        user.setPassword(encoder.encode(password));
        userMapper.insert(user);
        UserRoleRelation relation = new UserRoleRelation();
        relation.setRoleId(2L);
        relation.setUserId(user.getId());
        userRoleRelationMapper.insert(relation);
        return "index";
    }

    /**
     * 获取可用状态下的UserIds，封装成一个Set返回
     * @return
     */
    public Set<Long> getEnableUserIds(){
        LambdaQueryWrapper<User> userQw=new LambdaQueryWrapper<>();
        userQw.eq(User::isStatus,true);
        List<User> enableUserList = userService.list(userQw);
        Set<Long> enableUserIds = enableUserList.stream().map(User::getId).collect(Collectors.toSet());
        return enableUserIds;
    }

}
