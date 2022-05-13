package com.sinn.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sinn.mapper.RoleMapper;
import com.sinn.mapper.UserRoleRelationMapper;
import com.sinn.pojo.Role;
import com.sinn.pojo.User;
import com.sinn.pojo.UserRoleRelation;
import com.sinn.service.RoleService;
import com.sinn.service.UserRoleRelationService;
import com.sinn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 自定义用户认证接服务
 * @Author: Sitweling
 * @CreateTime: 2022/5/12
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserRoleRelationMapper userRoleRelationMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userService.getOne(queryWrapper);

        LambdaQueryWrapper<UserRoleRelation> queryWrapper2=new LambdaQueryWrapper<>();
        queryWrapper2.ge(UserRoleRelation::getUserId,user.getId());
        List<UserRoleRelation> selectList = userRoleRelationMapper.selectList(queryWrapper2);
        Set<Long> roleIds = selectList.stream().map(UserRoleRelation::getRoleId).collect(Collectors.toSet());

        LambdaQueryWrapper<Role> queryWrapper3=new LambdaQueryWrapper<>();
        queryWrapper3.in(Role::getId,roleIds);
        List<Role> roles = roleMapper.selectList(queryWrapper3);
        return toUserDetails(user,roles);
    }

    private UserDetails toUserDetails(User user, List<Role> roles){
        List<GrantedAuthority> authorityList=new ArrayList<>();
        for(Role role :roles){
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            authorityList.add(authority);
        }
        UserDetails userDetails=new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), user.getIsBan(),true,true,true,authorityList);
        return userDetails;
    }
}
