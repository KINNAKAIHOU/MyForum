package com.sinn.pojo;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 图片实体类
 * @Author: Sitweling
 * @CreateTime: 2022/5/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_picture",autoResultMap = true)
public class Picture {
    private Long pictureId;
    private String name;
    private String location;
    private LineOfCallerConverter createTime;
    @TableField(exist = false)
    private Blog blog;
    @TableLogic
    private Integer isDelete;
}
