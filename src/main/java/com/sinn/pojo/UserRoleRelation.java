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
@TableName("t_user_role")
public class UserRoleRelation {
    private Long id;
    private Long roleId;
    private Long userId;
}
