package com.sinn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @Description: 数据库自定义用户认证服务
 * @Author: Sitweling
 * @CreateTime: 2022/5/12
 */
public class WebSecurityConfigDB extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    //用户名、密码、状态
    String pwdQuery="SELECT u.user_name, u.password, is_ban FROM t_user t WHERE user_name=?";
    //第二个字段表示角色名
    String roleQuery="SELECT u.user_name, r.role_name FROM t_user u, t_user_role ur, t_role r" +
            "WHERE u.id=ur.user_id AND r.id=ur.role_id AND u.user_name=?";

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder)
                .dataSource(dataSource)
                .usersByUsernameQuery(pwdQuery)
                .authoritiesByUsernameQuery(roleQuery);
    }

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/loginUser/**").hasAnyRole("ADMIN","USER")
                .anyRequest().permitAll()
                .and()
                .anonymous()
                .and()
                .formLogin()
                .loginPage("/toLogin")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/index")
                .and()
                .httpBasic();
        http.csrf().disable();
    }
}
