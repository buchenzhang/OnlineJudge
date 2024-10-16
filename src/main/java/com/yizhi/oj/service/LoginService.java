package com.yizhi.oj.service;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.entity.User;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Zing
 * @Date: 2023/03/19/20:46
 * @Description:
 */
public interface LoginService {

    public ResponseResult login(User user, HttpServletResponse response);

    public ResponseResult logout(String id);

}
