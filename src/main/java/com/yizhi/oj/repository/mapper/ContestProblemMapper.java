package com.yizhi.oj.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yizhi.oj.repository.entity.ContestProblem;

import java.util.List;

/**
 * @Author: Zing
 * @Date: 2023/04/12/15:15
 * @Description:
 */
public interface ContestProblemMapper extends BaseMapper<ContestMapper> {

    Integer insertPro(ContestProblem problem);

    Integer getByCidPid(Long cid, Long pid);

    Integer delByPidCid(Integer id);

//    Integer delProByCid(List<Integer> problemList);
}
