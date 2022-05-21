package com.sinn.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sinn.mapper.BlogMapper;
import com.sinn.mapper.UserMapper;
import com.sinn.pojo.Blog;
import com.sinn.pojo.Favorite;
import com.sinn.pojo.User;
import com.sinn.pojo.Vo.UserVo;
import com.sinn.service.FavoriteService;
import com.sinn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/15
 */
@Controller
@RequestMapping("/favorites")
@Slf4j
public class FavoriteController {

    @Autowired
    UserService userService;

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    BlogMapper blogMapper;

    /**
     * 从首页点进去自己收藏的微博，跳转页面并且显示微博
     *
     * @param name
     * @param model
     * @param session
     * @param auth
     * @return
     */
    @GetMapping("/{name}/blogs")
    public String toMyFavorite(@PathVariable("name") String name, Model model, HttpSession session, Authentication auth) {
        User loginUser = (User) session.getAttribute("user");
        log.info("从session中获取到的user对象是：" + loginUser);
        if (!Objects.equals(loginUser.getUserName(), name)) {
            log.warn("非法访问！");
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, name);
        User nowUser = userService.getOne(queryWrapper);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(nowUser, userVo);
        log.info("现在的UserVo是" + userVo);
        log.info("UserVo的其他属性？" + userVo.getUserName() + "   " + userVo.getId());
        LambdaQueryWrapper<Favorite> favoriteQW = new LambdaQueryWrapper<>();
        favoriteQW.eq(Favorite::getUserId, userVo.getId());
        List<Favorite> favoriteList = favoriteService.list(favoriteQW);
        Set<Long> blogIds = favoriteList.stream().map(Favorite::getBlogId).collect(Collectors.toSet());
        if (blogIds.size() > 0) {
            List<Blog> blogList = blogMapper.selectList(Wrappers.lambdaQuery(Blog.class).in(Blog::getId, blogIds));
            userVo.setFavorites(blogList);
        }
        log.info("现在塞入attribute的userVo是：" + userVo);
        model.addAttribute("userVo", userVo);
        return "/loginUser/favorites";
    }

    /**
     * 删除收藏的微博
     *
     * @param blogId
     * @param session
     * @return
     */
    @RequestMapping("/{blogId}/delete")
    public String deleteFavorite(@PathVariable("blogId") Long blogId, HttpSession session) {
        User loginUser = (User) session.getAttribute("user");
        log.info("从session中获取到的user对象是：" + loginUser);
        LambdaQueryWrapper<Favorite> favoriteQW = new LambdaQueryWrapper<>();
        favoriteQW.eq(Favorite::getUserId, loginUser.getId())
                .eq(Favorite::getBlogId, blogId);
        favoriteService.remove(favoriteQW);
        log.info("收藏已经删除");
        return "redirect:" + "/favorites/" + loginUser.getUserName() + "/blogs";
    }
}
