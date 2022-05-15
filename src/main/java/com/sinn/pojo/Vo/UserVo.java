package com.sinn.pojo.Vo;

import com.sinn.pojo.Blog;
import com.sinn.pojo.Role;
import com.sinn.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    //权限集合
    List<Role> roles;
    //收藏集合
    List<Blog> favorites=new ArrayList<>();

}
