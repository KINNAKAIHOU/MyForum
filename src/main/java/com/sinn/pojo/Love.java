package com.sinn.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_love")
public class Love {
    private Long id;
    private Long userId;
    private Long blogId;
}
