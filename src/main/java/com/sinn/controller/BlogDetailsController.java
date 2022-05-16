package com.sinn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sinn.mapper.PictureMapper;
import com.sinn.mapper.UserMapper;
import com.sinn.pojo.*;
import com.sinn.pojo.Vo.BlogVo;
import com.sinn.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/15
 */
@Controller
@Slf4j
@RequestMapping("/details")
public class BlogDetailsController {

    @Autowired
    BlogService blogService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PictureService pictureService;

    @Autowired
    LoveService loveService;

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    CommentService commentService;

    /**
     * 查看微博的详细页面
     * @param blogId
     * @param model
     * @return
     */
    @RequestMapping("/{blogId}")
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

        //查询拥有的喜欢的人
        LambdaQueryWrapper<Love> LoveQw=new LambdaQueryWrapper<>();
        LoveQw.eq(Love::getBlogId,blogVo.getId());
        List<Love> loveList = loveService.list(LoveQw);
        Set<Long> collectUserIds = loveList.stream().map(Love::getUserId).collect(Collectors.toSet());
        if(collectUserIds.size()>0){
            List<User> loveUsers = userMapper.selectList(new LambdaQueryWrapper<User>().in(User::getId, collectUserIds));
            blogVo.setLoveUsers(loveUsers);
        }

        //查询拥有收藏的人
        LambdaQueryWrapper<Favorite> FavoriteQw=new LambdaQueryWrapper<>();
        FavoriteQw.eq(Favorite::getBlogId,blogVo.getId());
        List<Favorite> favoriteList = favoriteService.list(FavoriteQw);
        Set<Long> collectFavoriteUserIds = favoriteList.stream().map(Favorite::getUserId).collect(Collectors.toSet());
        if(collectFavoriteUserIds.size()>0){
            List<User> favoriteUsers = userMapper.selectList(new LambdaQueryWrapper<User>().in(User::getId, collectFavoriteUserIds));
            blogVo.setFavoriteUsers(favoriteUsers);
        }

        //查询分享下面的评论
        LambdaQueryWrapper<Comment> CommentQw=new LambdaQueryWrapper<>();
        CommentQw.eq(Comment::getBlogId,blogVo.getId());
        List<Comment> commentList = commentService.list(CommentQw);
        if(commentList.size()>0){
            blogVo.setComments(commentList);
        }

        model.addAttribute("blogVo",blogVo);
        log.info("此时的blogVo为"+blogVo);
        return "blog";
    }

    /**
     * 用户喜欢微博
     * @param blogId
     * @param session
     * @return
     */
    @RequestMapping("/{blogId}/like")
    public String addLikeBlog(@PathVariable("blogId") Long blogId, HttpSession session){
        log.info("调用喜欢功能");
        User loginUser = (User) session.getAttribute("user");
        Love love = new Love();
        love.setBlogId(blogId);
        love.setUserId(loginUser.getId());
        //添加save关系到数据库
        loveService.save(love);
        //blog的loveCount+1
        LambdaUpdateWrapper<Blog> blogUw=new LambdaUpdateWrapper<>();
        blogUw.eq(Blog::getId,blogId)
                .setSql("love_count = love_count+1");
        blogService.update(blogUw);

        return "redirect:/details/"+blogId;
    }

    /**
     * 用户取消喜欢微博
     * @param blogId
     * @param session
     * @return
     */
    @RequestMapping("/{blogId}/dislike")
    public String dislikeBlog(@PathVariable("blogId") Long blogId, HttpSession session) {
        log.info("用户取消喜欢分享");
        //1.微博喜欢人数-1
        LambdaUpdateWrapper<Blog> blogUw=new LambdaUpdateWrapper<>();
        blogUw.eq(Blog::getId,blogId)
                .setSql("love_count = love_count-1");
        blogService.update(blogUw);
        //2.删除关联表关系
        User loginUser = (User) session.getAttribute("user");
        LambdaQueryWrapper<Love> loveQw=new LambdaQueryWrapper<>();
        loveQw.eq(Love::getBlogId,blogId)
                .eq(Love::getUserId,loginUser.getId());
        loveService.remove(loveQw);

        return "redirect:/details/"+blogId;
    }

    /**
     * 用户收藏一个分享
     * @param blogId
     * @param session
     * @return
     */
    @RequestMapping("/{blogId}/favorite")
    public String addFavoriteBlog(@PathVariable("blogId") Long blogId, HttpSession session){
        log.info("用户收藏分享");
        //1.微博收藏人数+1
        LambdaUpdateWrapper<Blog> blogUw=new LambdaUpdateWrapper<>();
        blogUw.eq(Blog::getId,blogId)
                .setSql("favorite_count = favorite_count+1");
        blogService.update(blogUw);
        //2.添加关联表关系
        User loginUser = (User) session.getAttribute("user");
        Favorite favorite = new Favorite();
        favorite.setBlogId(blogId);
        favorite.setUserId(loginUser.getId());
        favoriteService.save(favorite);

        return "redirect:/details/"+blogId;
    }

    @RequestMapping("/{blogId}/disfavorite")
    public String disFavoriteBlog(@PathVariable("blogId") Long blogId, HttpSession session){
        log.info("用户取消收藏分享");
        User loginUser = (User) session.getAttribute("user");
        //1.微博收藏人数-1
        LambdaUpdateWrapper<Blog> blogUw=new LambdaUpdateWrapper<>();
        blogUw.eq(Blog::getId,blogId)
                .setSql("favorite_count = favorite_count-1");
        blogService.update(blogUw);
        //2.删除关联表关系
        LambdaQueryWrapper<Favorite> favoriteQw=new LambdaQueryWrapper<>();
        favoriteQw.eq(Favorite::getBlogId,blogId)
                .eq(Favorite::getUserId,loginUser.getId());
        favoriteService.remove(favoriteQw);

        return "redirect:/details/"+blogId;
    }
}
