package com.yizhi.oj.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yizhi.oj.repository.entity.ProblemCase;

import java.util.List;

/**
 * @Author: Zing
 * @Date: 2023/04/08/21:06
 * @Description:
 */
public interface ProblemCaseMapper extends BaseMapper<ProblemCase> {
    /**
     * 批量添加测试点
     * @param problemCaseList 测试点集合
     * @return
     */
    Integer addProCases(ProblemCase problemCaseList);

    /**
     * 查询指定pid的全部测试数据
     * @param pid 题目id
     * @return 全部测试点
     */
    List<ProblemCase> selectByPid(Long pid);
}
