package com.sinn.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 评论实体类
 * @Author: Sitweling
 * @CreateTime: 2022/5/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_comment")
//实体类字段和数据库字段一一对应
public class Comment {
    private Long id;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private String userName;

    private Long BlogId;
   /* @TableField(exist = false)
    private Comment parentComment;
    //回复评论
    @TableField(exist = false)
    private List<Comment> replyComment;
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Blog blog;*/
}
