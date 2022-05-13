package com.sinn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sinn.mapper.UserMapper;
import com.sinn.mapper.UserRoleRelationMapper;
import com.sinn.pojo.User;
import com.sinn.pojo.UserRoleRelation;
import com.sinn.service.UserService;
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
    UserRoleRelationMapper userRoleRelationMapper;
    /**
     * 直接进入首页
     * 判断是否已经登陆，未登录则直接进入首页
     * 已登陆的情况下，将User对象放入Session中
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
     * 通过MP字段自动注入完成注册日期
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

}
