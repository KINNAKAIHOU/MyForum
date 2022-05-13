package com.sinn.controller;

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
    UserRoleRelationMapper userRoleRelationMapper;
    /**
     * 直接进入首页
     * @return index
     */
    @RequestMapping({"/","/index"})
    public String index(Model model, Authentication auth) {
        UserDetails userDetails = null;
        if (auth != null) {
            userDetails = (UserDetails) auth.getPrincipal();
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


    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
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
