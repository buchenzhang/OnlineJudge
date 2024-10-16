package com.yizhi.oj.service.impl;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.mapper.UserMapper;
import com.yizhi.oj.repository.entity.User;
import com.yizhi.oj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zing
 * @Date: 2023/03/19/19:54
 * @Description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public ResponseResult registration(String id, String password,String username) {
        if (userMapper.getOne(id) != null){
            return new ResponseResult(200,"此学号已注册，请登录");
        }
        password = bCryptPasswordEncoder.encode(password);
        userMapper.registration(id,password,username);

        return new ResponseResult(200,"注册成功，请登录");
    }

    @Override
    public ResponseResult reviseUserInfo(User user) {
        userMapper.update(user);
        return new ResponseResult<>(200,"修改成功");
    }

    @Override
    public ResponseResult page(Integer page, Integer pageSize) {
        page = (page - 1) * pageSize;
        List<User> userList = userMapper.selectByPage(page, pageSize);
        Map<Object,Object> res = new HashMap<>();
        res.put("total",userMapper.selectAll());
        res.put("info",userList);
        return new ResponseResult<>(200,res);
    }

}
