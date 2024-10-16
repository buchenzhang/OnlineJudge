package com.yizhi.oj.repository.mapper;

import com.yizhi.oj.repository.entity.User;

import java.util.List;

/**
 * @Author: Zing
 * @Date: 2023/03/19/19:55
 * @Description:
 */
public interface UserMapper {

    User getOne(String id);

    Integer registration(String id, String password,String username);

    Integer update(User user);

    List<User> selectByPage(Integer page, Integer pageSize);

    Integer selectAll();
}
