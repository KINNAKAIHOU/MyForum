package com.sinn.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Description: User实体类
 * @Author: Sitweling
 * @CreateTime: 2022/5/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_user")
public class User {
    //本身字段
    private Long id;
    private String userName;
    private String password;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private Boolean isBan;
    /*//以下是一对多
    @TableField(exist = false)
    private List<Blog> blogs;

    @TableField(exist = false)
    private List<Blog> loves;

    @TableField(exist = false)
    private List<Blog> favorites;

    @TableField(exist = false)
    private List<Comment> comments;*/
}
