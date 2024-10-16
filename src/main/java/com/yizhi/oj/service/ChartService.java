package com.yizhi.oj.service;

import com.yizhi.oj.common.ResponseResult;

/**
 * @Author: Zing
 * @Date: 2023/05/17/16:57
 * @Description:
 */
public interface ChartService {
    ResponseResult weekByDay();

    ResponseResult oneChart(Integer pid);

    ResponseResult sysInfo();

}
