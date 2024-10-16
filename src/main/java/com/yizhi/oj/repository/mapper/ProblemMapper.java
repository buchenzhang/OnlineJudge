package com.yizhi.oj.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yizhi.oj.repository.dto.ProblemDTO;
import com.yizhi.oj.repository.entity.Problem;

import java.util.List;

/**
 * @Author: Zing
 * @Date: 2023/04/08/20:07
 * @Description:
 */
public interface ProblemMapper extends BaseMapper<Problem> {

    List<Problem> selectByPage(Integer page, Integer pageSize);

    Integer selectAll();

    /**
     * 根据id 标题 内容搜索
     * @param msg
     * @return
     */
    List<Problem> getByITT(String msg,Integer page,Integer pageSize);

    Integer selectAllByITT(String msg);

    List<ProblemDTO> getByCid(Long cid);

    List<Problem> selectByPageAll(Integer page, Integer pageSize);
}
