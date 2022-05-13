package com.sinn.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/12
 */
@Slf4j
//@Configuration
public class WebSecurityConfigUD extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        log.info("认证完成");
    }

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        log.info("开始授权");
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/loginUser/**").hasAnyRole("ADMIN","USER")
                .anyRequest().permitAll()
                .and()
                .anonymous()
                .and()
                .formLogin()
                .loginPage("/toLogin")
                .failureForwardUrl("/toLogin")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/index")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
//        log.info("完成授权");
    }
}
