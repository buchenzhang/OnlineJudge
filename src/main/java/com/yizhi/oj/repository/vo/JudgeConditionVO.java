package com.yizhi.oj.repository.vo;

import com.yizhi.oj.repository.dto.SingleJudgeResultDTO;
import lombok.Data;

import java.util.List;

/**
 * @Author: Zing
 * @Date: 2023/04/09/13:40
 * @Description: 对某次判题最终结果的表现层封装
 */
@Data
public class JudgeConditionVO {
    private final List<SingleJudgeResultDTO> judgeResults;
    private final String submissionId;
    private final Long judgeEndTime;
    private final List<String> extraInfo;
}
