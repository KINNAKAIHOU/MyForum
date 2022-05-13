package com.sinn.pojo.Vo;

import com.sinn.pojo.Role;
import com.sinn.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo extends User {
    List<Role> roles;
}
