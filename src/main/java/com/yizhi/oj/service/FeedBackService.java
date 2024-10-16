package com.yizhi.oj.service;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.entity.FeedBack;

/**
 * @Author: Zing
 * @Date: 2023/04/18/12:44
 * @Description:
 */
public interface FeedBackService {
    ResponseResult add(FeedBack feedBack);

    ResponseResult dispose(Long id);

    ResponseResult page(Integer page, Integer pageSize);
}
