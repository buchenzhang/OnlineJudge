package com.yizhi.oj.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yizhi.oj.repository.entity.FeedBack;

import java.util.List;

/**
 * @Author: Zing
 * @Date: 2023/04/18/12:43
 * @Description:
 */
public interface FeedBackMapper extends BaseMapper<FeedBack> {
    Integer updateState(Long id);

    List<FeedBack> getByPage(Integer page, Integer pageSize);

    Integer getAll();
}
