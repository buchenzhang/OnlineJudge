package com.yizhi.oj.repository.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Author: Zing
 * @Date: 2023/04/09/13:11
 * @Description: 提交到判题机的模型
 */
@Data
public class JudgeDTO {

    @NotNull(message = "代码不得为空")
    private String submissionCode;

    @DecimalMax(value = "10000", message = "实际时间限制最大为10 * 1000ms")
    private Integer realTimeLimit;

    @DecimalMax(value = "10000", message = "cpu时间限制最大为10 * 1000ms")
    private Integer cpuTimeLimit;

    @DecimalMin(value = "3000", message = "内存限制最小为3000kb")
    @DecimalMax(value = "65536", message = "内存限制最大为65536kb")
    private Integer memoryLimit;

    @DecimalMin(value = "10", message = "输出限制最小为10Byte")
    private Integer outputLimit;

    @NotNull(message = "语言不得为空")
    private String language;

    /**
     * ACM或者OI
     */
    private String judgePreference;

    @NotNull(message = "期望输入、输出不得为空")
    @Size(message = "期望输入、输出长度最小为1、最大为10", min = 1, max = 10)
    @Valid
    private List<SolutionDTO> solutions;

}
