package com.yizhi.oj.service;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.entity.User;

/**
 * @Author: Zing
 * @Date: 2023/03/19/19:53
 * @Description:
 */
public interface UserService {

    ResponseResult registration(String id, String password,String username);

    ResponseResult reviseUserInfo(User user);

    ResponseResult page(Integer page, Integer pageSize);
}
