package com.yizhi.oj.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yizhi.oj.repository.entity.Contest;

import java.util.List;

/**
 * @Author: Zing
 * @Date: 2023/04/12/14:50
 * @Description:
 */
public interface ContestMapper extends BaseMapper<Contest> {

    List<Contest> getByPage(Integer page, Integer pageSize);

    Integer getAll();
}
