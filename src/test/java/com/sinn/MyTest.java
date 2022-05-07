package com.sinn;

import com.sinn.mapper.UserMapper;
import com.sinn.pojo.User;
import com.sinn.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/6
 */
@SpringBootTest
public class MyTest {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Test
    public void test1(){
        User user = userService.getOne(null);
        System.out.println(user);
    }

    @Test
    public void test2(){
        User user = new User();
        user.setUserName("测试账号X");
        user.setPassword("123456");
        userMapper.insert(user);
        System.out.println(user);
    }
    @Test
    public void test3(){
        User user = new User();
        user.setUserId(2L);
        user.setUserName("测试账号4");
        int i = userMapper.updateById(user);
        System.out.println(user);
    }

}
