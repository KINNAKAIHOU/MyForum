package com.sinn.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_role")
public class Role {
    private Long id;
    private String roleName;
    private String note;
}
