package com.yizhi.oj.repository.dto;

import com.yizhi.oj.repository.Enum.JudgeResultEnum;
import lombok.Data;

/**
 * @Author: Zing
 * @Date: 2023/04/09/13:42
 * @Description: 单次判题的数据传输对象
 */
@Data
public class SingleJudgeResultDTO {
    private String realTimeCost;
    private String memoryCost;
    private String cpuTimeCost;
    private Integer condition;
    private String stdInPath;
    private String stdOutPath;
    private String stdErrPath;
    private String message;

    /**
     * 设置message，根据condition的数值，
     * 利用枚举类型转换成message以备返回给前端
     *
     * @author yuzhanglong
     * @date 2020-6-29 22:54:47
     */
    public void setMessageWithCondition() {
        JudgeResultEnum type = JudgeResultEnum.toJudgeResultType(this.condition);
        this.message = type.getMessage();
    }
}
