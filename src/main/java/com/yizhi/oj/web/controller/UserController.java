package com.yizhi.oj.web.controller;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.entity.User;
import com.yizhi.oj.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Zing
 * @Date: 2023/03/19/19:49
 * @Description:
 */
@RestController
@Api(tags = "用户控制器")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/registration")
    @ApiOperation("用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "学号",required = true,dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query",name = "password",value = "密码",required = true,dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query",name = "username",value = "昵称",required = true,dataTypeClass = String.class)
    })

    public ResponseResult registration(@RequestParam("id") String id,
                                       @RequestParam("password") String password,
                                       @RequestParam("username") String username){
        return userService.registration(id,password,username);
    }

    @PostMapping("/reviseUserInfo")
    @ApiOperation("修改个人信息")
    public ResponseResult reviseUserInfo(@RequestBody User user){
        return userService.reviseUserInfo(user);
    }

    @ApiOperation("分页查询全部用户")
    @GetMapping("/page")
    public ResponseResult page(@RequestParam("page")Integer page,@RequestParam Integer pageSize){
        return userService.page(page,pageSize);
    }


}
