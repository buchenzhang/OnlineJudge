package com.yizhi.oj.service;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.entity.Judge;

/**
 * @Author: Zing
 * @Date: 2023/04/09/11:56
 * @Description:
 */
public interface CommitService {
    ResponseResult commit(Judge judge);

    ResponseResult oneProCommit(Long pid,Integer page,Integer pageSize);

    ResponseResult allProCommit(Integer page, Integer pageSize);

}
