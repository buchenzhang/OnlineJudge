package com.yizhi.oj.service.impl;

import com.yizhi.oj.repository.dto.LoginUser;
import com.yizhi.oj.repository.mapper.UserMapper;
import com.yizhi.oj.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Zing
 * @Date: 2023/03/19/20:23
 * @Description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;


    //实现UserDetailsService接口，重写UserDetails方法，自定义用户的信息从数据中查询
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //（认证，即校验该用户是否存在）查询用户信息
        User user = userMapper.getOne(username);

        //如果没有查询到用户
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }

        // (授权，即查询用户具有哪些权限)查询对应的用户信息
        String role = userMapper.getOne(username).getRole();
        List<String> list = new ArrayList<>();
        list.add(role);
        //把数据封装成UserDetails返回
        return new LoginUser(user,list);
    }
}
