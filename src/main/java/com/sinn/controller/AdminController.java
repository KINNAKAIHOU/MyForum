package com.sinn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sinn.pojo.User;
import com.sinn.pojo.Violate;
import com.sinn.service.BlogService;
import com.sinn.service.UserService;
import com.sinn.service.ViolateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/13
 */
@Controller
@Slf4j
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private ViolateService violateService;

    /**
     * 前往管理员登陆页面
     */
    @RequestMapping("/admin")
    public String toAdminLogin(HttpSession session, Model model) {
        log.info("查询全部数据");
        User LoginUser = (User) session.getAttribute("user");
        LambdaQueryWrapper<User> userQw = new LambdaQueryWrapper<>();
        userQw.notIn(User::getUserName, LoginUser.getUserName())
                .orderByDesc(User::getId);
        List<User> users = userService.list(userQw);
        model.addAttribute("users", users);
        return "/admin/checkUser";
    }

    /**
     * 封禁用户账号
     *
     * @param userId
     * @return
     */
    @RequestMapping("/admin/{userId}/ban")
    public String banUser(@PathVariable("userId") Long userId) {
        log.info("开始封禁账号");
        LambdaQueryWrapper<User> userQw = new LambdaQueryWrapper<>();
        userQw.eq(User::getId, userId);
        User user = userService.getOne(userQw);
        user.setStatus(false);
        userService.updateById(user);
        return "redirect:/admin";
    }


    @RequestMapping("/admin/{userId}/open")
    public String openUser(@PathVariable("userId") Long userId) {
        log.info("开始启动账号");
        LambdaQueryWrapper<User> userQw = new LambdaQueryWrapper<>();
        userQw.eq(User::getId, userId);
        User user = userService.getOne(userQw);
        user.setStatus(true);
        userService.updateById(user);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/violate")
    public String toViolate(Model model) {
        List<Violate> violates = violateService.list();
        model.addAttribute("violates", violates);
        return "/admin/checkViolate";
    }

    @RequestMapping("/admin/violate/add")
    public String addViolate(@RequestParam("violateName") String violateName) {
        log.info("添加违禁词语");
        Violate violate = new Violate();
        violate.setViolateName(violateName);
        violateService.save(violate);
        return "redirect:/admin/violate";
    }

    @RequestMapping("/admin/violate/{violateId}")
    public String addViolate(@PathVariable("violateId") Long violateId) {
        log.info("删除违禁词语");
        LambdaQueryWrapper<Violate> violateQW = new LambdaQueryWrapper<>();
        violateQW.eq(Violate::getId, violateId);
        violateService.remove(violateQW);
        return "redirect:/admin/violate";
    }
}
