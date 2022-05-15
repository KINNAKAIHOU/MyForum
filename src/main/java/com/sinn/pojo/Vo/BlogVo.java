package com.sinn.pojo.Vo;

import com.sinn.pojo.Blog;
import com.sinn.pojo.Comment;
import com.sinn.pojo.Picture;
import com.sinn.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogVo extends Blog {
    //博主的昵称
    private String userName;

    //微博里面的图片
    private List<Picture> pictures;

    //评论区
    private List<Comment> comments;

    //喜欢的人的列表
    private List<User> loveUsers;

    //收藏的人的列表
    private List<User> favoriteUsers;
}
