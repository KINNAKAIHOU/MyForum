package com.sinn.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Description: 微博实体类
 * @Author: Sitweling
 * @CreateTime: 2022/5/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_blog")
//实体类字段和数据库字段一一对应
public class Blog {
    private Long id;

    private String title;

    private String content;

    private boolean shareStatement;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private Integer views;

    private Integer loveCount;

    private Integer favoriteCount;

    private boolean seeAble;

    private boolean loveAble;

    private boolean commentAble;

    private Long userId;

    private Integer isDelete;
    /*//外键约束转化成实体对象
    @TableField(exist = false)
    private User user;
    @TableLogic
    private Integer isDelete;

    //一对多等关系
    @TableField(exist = false)
    private List<Picture> pictures;

    @TableField(exist = false)
    private List<Comment> comments;

    @TableField(exist = false)
    private List<User> loves;

    @TableField(exist = false)
    private List<User> favorites;*/

}
