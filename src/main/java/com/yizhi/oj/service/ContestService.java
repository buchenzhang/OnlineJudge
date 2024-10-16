package com.yizhi.oj.service;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.entity.Contest;
import com.yizhi.oj.repository.entity.ContestProblem;

import java.util.List;

/**
 * @Author: Zing
 * @Date: 2023/04/12/14:51
 * @Description:
 */
public interface ContestService {
    ResponseResult creat(Contest contest);

    ResponseResult delete(Long id);

    ResponseResult update(Contest contest);


    ResponseResult addProblems(ContestProblem problem);

    ResponseResult deleteProByCid(Integer id);

    ResponseResult proByCid(Long cid);

    ResponseResult page(Integer page, Integer pageSize);

    ResponseResult authentication(String pwd,Integer id);
}
