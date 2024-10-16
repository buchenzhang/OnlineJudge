package com.yizhi.oj.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yizhi.oj.repository.dto.OneWeekJudgeDTO;
import com.yizhi.oj.repository.entity.Judge;

import java.util.List;
import java.util.Map;

/**
 * @Author: Zing
 * @Date: 2023/04/09/11:54
 * @Description:
 */
public interface JudgeMapper extends BaseMapper<Judge> {


    List<Judge> getByPidPage(Long pid, Integer page, Integer pageSize);

    List<Judge> getAllPage(Integer page, Integer pageSize);

    Integer getAll();

    Integer getAllByPid(Long pid);

    /**
     * 一道题的通过数量
     * @param pid 题目id
     */
    Integer getByIdStatus(Integer pid);

    /**
     * 一道题的wa数量
     * @param pid 题目id
     * @return
     */
    Integer getOneNotPass(Integer pid);

    List<OneWeekJudgeDTO> weekByDay();
}
