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
@TableName(value = "t_comment",autoResultMap = true)
public class Comment {
    @TableId(type = IdType.AUTO)
    private Long commentId;
    private String content;
    private LocalDateTime createTime;
    @TableField(exist = false)
    private Comment parentComment;
    //回复评论
    @TableField(exist = false)
    private List<Comment> replyComment;
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Blog blog;
    @TableLogic
    private Integer isDelete;
}
