package com.yizhi.oj.web.controller;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.entity.User;
import com.yizhi.oj.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Zing
 * @Date: 2023/03/19/20:33
 * @Description:
 */
@RestController
@Api(tags = "登录控制器")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResponseResult login(@RequestBody User user, HttpServletResponse response){
        return loginService.login(user,response);
    }

    @GetMapping("/logout")
    @ApiOperation("用户退出")
    public ResponseResult logout(@RequestParam("id")String id){
        return loginService.logout(id);
    }




}
