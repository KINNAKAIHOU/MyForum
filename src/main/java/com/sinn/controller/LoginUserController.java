package com.sinn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sinn.pojo.Blog;
import com.sinn.pojo.User;
import com.sinn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/13
 */
@Controller
@RequestMapping("/loginUser")
public class LoginUserController {

    @Autowired
    UserService userService;

    /*@{/loginUser/{name}/blogs(name=${userDetails.getUsername()})}*/

    /**
     * 点击查看个人发布的全部微博
     * @param name
     * @param model
     * @param auth
     * @return
     */
    @RequestMapping("/{name}/blogs")
    public String toMyBlogs(@PathVariable String name, Model model, Authentication auth, HttpServletRequest request){
        model.addAttribute("userDetails", auth.getPrincipal());
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,name);
        User user = userService.getOne(queryWrapper);
        request.getSession().setAttribute("user",user);
        return "loginUser/blogs";
    }

    //@{/loginUser/{name}/blogInput(name=${session.user.getUserName()})}
    @RequestMapping("{name}/blogInput")
    public String myBlogInput(@PathVariable String name){
        return "loginUser/blog-input";
    }

    @PostMapping("/release")
    public String releaseBlog(Blog blog, RedirectAttributes attributes, HttpSession session){
        User user = (User) session.getAttribute("user");
        String userName = user.getUserName();

        return "redirect:/loginUser/"+userName+"/blogs";
    }
}
