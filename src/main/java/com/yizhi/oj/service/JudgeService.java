package com.yizhi.oj.service;

import com.yizhi.oj.repository.dto.AuthorizationDTO;
import com.yizhi.oj.repository.dto.JudgeDTO;
import com.yizhi.oj.repository.vo.AuthorizationVO;
import com.yizhi.oj.repository.vo.JudgeConditionVO;

/**
 * @Author: Zing
 * @Date: 2023/04/25/18:50
 * @Description:
 */
public interface JudgeService {

    public AuthorizationVO getAuth();

    public JudgeConditionVO judge(JudgeDTO judgeDTO);
}
