package com.sinn.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinn.mapper.RoleMapper;
import com.sinn.pojo.Role;
import com.sinn.pojo.User;
import com.sinn.service.RoleService;
import com.sinn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/12
 */
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
