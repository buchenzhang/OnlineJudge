package com.yizhi.oj.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yizhi.oj.repository.entity.ContestList;

/**
 * @Author: Zing
 * @Date: 2023/05/01/14:22
 * @Description:
 */
public interface ContestListMapper extends BaseMapper<ContestList> {

    ContestList getByCidAndUid(Long cid, String uid);
}
