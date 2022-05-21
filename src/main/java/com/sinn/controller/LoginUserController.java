package com.sinn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sinn.mapper.BlogMapper;
import com.sinn.pojo.Blog;
import com.sinn.pojo.Picture;
import com.sinn.pojo.User;
import com.sinn.pojo.Violate;
import com.sinn.pojo.Vo.BlogVo;
import com.sinn.service.BlogService;
import com.sinn.service.PictureService;
import com.sinn.service.UserService;
import com.sinn.service.ViolateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/13
 */
@Controller
@RequestMapping("/loginUser")
@Slf4j
public class LoginUserController {

    @Autowired
    UserService userService;

    @Autowired
    BlogService blogService;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    PictureService pictureService;

    @Autowired
    ViolateService violateService;


    /**
     * 点击查看个人发布的全部微博
     * 用户信息从Authentication中获取，增加了一丝的安全性。（笑
     *
     * @param name
     * @param model
     * @param auth
     * @return
     */
    @RequestMapping("/{name}/blogs")
    public String toMyBlogs(@PathVariable String name, Model model, Authentication auth, HttpServletRequest request) {
        model.addAttribute("userDetails", auth.getPrincipal());
        log.info("getPrincipal是：" + auth.getPrincipal().toString());
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, principal.getUsername());
        User user = userService.getOne(queryWrapper);
        request.getSession().setAttribute("user", user);
        LambdaQueryWrapper<Blog> blogWrapper = new LambdaQueryWrapper<>();
        blogWrapper.eq(Blog::getUserId, user.getId());
        List<Blog> blogs = blogMapper.selectList(blogWrapper);
        model.addAttribute("blogs", blogs);
        return "loginUser/blogs";
    }

    //@{/loginUser/{name}/blogInput(name=${session.user.getUserName()})}

    /**
     * 前往微博发布页面
     *
     * @param name
     * @return
     */
    @RequestMapping("{name}/blogInput")
    public String myBlogInput(@PathVariable String name, Model model) {
        log.info("前往微博发布页面");
        Blog blog = new Blog();
        blogService.save(blog);
        model.addAttribute("blog", blog);
        log.info("完成一次新的微博创建");
        return "loginUser/blog-input";
    }

    /**
     * 编辑微博内容
     *
     * @param blogId
     * @param model
     * @return
     */
    @RequestMapping("{blogId}/blogUpdate")
    public String myBlogChange(@PathVariable Integer blogId, Model model) {
        log.info("前往博客修改页面");
        Blog blog = blogService.getById(blogId);
        model.addAttribute("blog", blog);
        return "loginUser/blog-input";
    }

    /**
     * 发布新微博
     *
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/release")
    public String releaseBlog(Blog blog, RedirectAttributes attributes, HttpSession session, Model model) {
        log.info("确认发布一个博客");
        User user = (User) session.getAttribute("user");
        List<Violate> violates = violateService.list(null);
        for (Violate violate : violates) {
            if (blog.getContent().indexOf(violate.getViolateName()) != -1 || blog.getTitle().indexOf(violate.getViolateName()) != -1) {
                log.info("发布一个博客失败");
                blog.setShareStatement(false);
                model.addAttribute("blog", blog);
                model.addAttribute("violateMsg", "含有违禁词语");
                return "loginUser/blog-input";
            }
        }
        System.out.println(blog);
        blog.setUserId(user.getId());
        log.info("blog Id是" + blog.getId().toString());
        boolean res = blogService.updateById(blog);
        if (res) {
            attributes.addFlashAttribute("msg", "操作成功");
        } else {
            attributes.addFlashAttribute("msg", "操作失败");
        }
        return "redirect:/loginUser/" + user.getUserName() + "/blogs";
    }

    /**
     * 删除新微博
     *
     * @param blogId
     * @param session
     * @return
     */
    @RequestMapping("/{blogId}/delete")
    public String deleteBlog(@PathVariable("blogId") Integer blogId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        blogMapper.deleteById(blogId);
        return "redirect:/loginUser/" + user.getUserName() + "/blogs";
    }
}
