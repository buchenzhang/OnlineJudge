package com.yizhi.oj.service;

import com.yizhi.oj.common.ResponseResult;
import com.yizhi.oj.repository.entity.Problem;
import com.yizhi.oj.repository.entity.ProblemCase;

import java.util.List;

/**
 * @Author: Zing
 * @Date: 2023/04/08/20:09
 * @Description:
 */
public interface ProblemService {

    ResponseResult add(Problem problem);

    ResponseResult update(Problem problem);

    ResponseResult delete(Long id);

    ResponseResult page(Integer page, Integer pageSize);

    /**
     * 上传指定id的测试数据
     * @param problemCaseList 测试数据
     * @return
     */
    ResponseResult addPros( ProblemCase problemCaseList);

    ResponseResult deleteCaseByIds(Long ids);

    ResponseResult getByITT(String msg,Integer page,Integer pageSize);

    ResponseResult allProCase(Long id);

    ResponseResult pageAll(Integer page, Integer pageSize);

    ResponseResult getById(Long id);
}
