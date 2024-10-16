package com.yizhi.oj.repository.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: Zing
 * @Date: 2023/04/09/13:18
 * @Description:
 */
@Data
@ApiModel("测试数据模型")
public class SolutionDTO {

    @NotNull(message = "输入不得为空")
    String stdIn;
    @NotNull(message = "期望输出不得为空")
    String expectedStdOut;
}
