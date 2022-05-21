package com.sinn.controller;

import com.sinn.pojo.Comment;
import com.sinn.pojo.User;
import com.sinn.pojo.Violate;
import com.sinn.service.CommentService;
import com.sinn.service.ViolateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/16
 */
@Controller
@Slf4j
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/comment")
    public String addComment(@RequestParam("blogId") Long blogId, @RequestParam("content") String content,
                             @RequestParam("parentCommentId") Long parentCommentId,
                             @RequestParam("rootCommentId") Long rootCommentId, HttpSession session) {
        User loginUser = (User) session.getAttribute("user");
        //1.新建评论存入数据库
        Comment comment = new Comment();
        comment.setBlogId(blogId);
        comment.setParentCommentId(parentCommentId);
        comment.setRootCommentId(rootCommentId);
        comment.setUserName(loginUser.getUserName());
        comment.setContent(content);
        commentService.save(comment);
        log.info("评论信息已保存！");
        return "redirect:/details/" + blogId;
    }

}
