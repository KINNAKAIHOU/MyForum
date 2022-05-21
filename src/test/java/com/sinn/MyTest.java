package com.sinn;

import com.sinn.mapper.UserMapper;
import com.sinn.pojo.User;
import com.sinn.pojo.Violate;
import com.sinn.service.UserService;
import com.sinn.service.ViolateService;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

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

    @Autowired
    ViolateService violateService;

    @Test
    public void test1(){
        User user = userService.getOne(null);
        System.out.println(user);
    }

    @Test
    public void test2(){
        User user = new User();
        user.setUserName("TestUser1");
        user.setPassword("123456");
        userMapper.insert(user);
        System.out.println(user);
    }
    @Test
    public void test3(){
        User user = new User();
        user.setId(13L);
        user.setUserName("测试账号4");
        user.setPassword("6452131");
        int i = userMapper.updateById(user);
        System.out.println(user);
    }

    @Test
    public void test4(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user=new User();
        user.setUserName("user123");
        String encode = encoder.encode("123456");
        user.setPassword(encode);
        userMapper.insert(user);
    }

    @Test
    public void Test5(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.matches("123456", encoder.encode("123456")));
    }

    @Test
    public void Test6(){
        List<Violate> violates= violateService.list();
        System.out.println(violates);
    }
}
