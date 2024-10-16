package com.yizhi.oj.service.impl;

import com.yizhi.oj.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.yizhi.oj.common.JWTUtil;
import com.yizhi.oj.repository.dto.LoginUser;
import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.entity.User;
import com.yizhi.oj.service.LoginService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Zing
 * @Date: 2023/04/09/18:05
 * @Description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginServiceImpl implements LoginService{

    private final AuthenticationManager authenticationManager;

    private final RedisTemplate redisTemplate;

    private final UserMapper userMapper;


    /**
     * 对学号加密
     * @param user
     * @return
     */
    @Override
    public ResponseResult login(User user, HttpServletResponse response) {

        try{
            //通过UsernamePasswordAuthenticationToken获取用户名和密码
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken
                    (user.getId(),user.getPassword());
            //AuthenticationManager委托机制对authenticationToken 进行用户认证
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);

            //如果认证没有通过，给出对应的提示
            if (Objects.isNull(authenticate)){
                throw new RuntimeException("登录失败");
            }

            //如果认证通过，拿到这个当前登录用户信息
            LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

            //获取当前用户的userid
            String userid = loginUser.getUser().getId().toString();

            String jwt = JWTUtil.createToken(userid);
            response.setHeader("Authorization",jwt);

            //把完整的用户信息存入redis  userid为key   用户信息为value
            redisTemplate.opsForValue().set("login_"+userid, JSONObject.toJSONString(loginUser));
            redisTemplate.opsForValue().set("token_" + userid,jwt,1, TimeUnit.DAYS);

            User res = userMapper.getOne(userid);
            res.setPassword(null);
            return new ResponseResult(200,"登录成功",res);
        }catch (BadCredentialsException e){
            return new ResponseResult<>(300,e.getMessage());
        }

    }

    @Override
    public ResponseResult logout(String id) {
        //从SecurityContextHolder中的userid
//        UsernamePasswordAuthenticationToken authentication =
//                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        String userid = loginUser.getUser().getId();
        String userid = id;

        //根据userid找到redis对应值进行删除
        redisTemplate.delete("login_"+userid);
        redisTemplate.delete("token_" + id);
        return new ResponseResult(200,"退出成功");
    }



}
