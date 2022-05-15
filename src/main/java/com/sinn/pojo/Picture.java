package com.sinn.pojo;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description: 图片实体类
 * @Author: Sitweling
 * @CreateTime: 2022/5/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_picture")
public class Picture {
    private Long id;

    private String name;

    private String location;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private Integer blogId;
   /* @TableField(exist = false)
    private Blog blog;*/
}
