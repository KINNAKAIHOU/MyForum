package com.sinn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/12
 */
@Controller
public class LoginController {
    /**
     * 直接进入首页
     * @return index
     */
    @RequestMapping({"/","index"})
    public String index(){
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
}
