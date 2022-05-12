package com.sinn.pojo.dto;

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
public class UserDto extends User {
    List<Role> roles;
}
