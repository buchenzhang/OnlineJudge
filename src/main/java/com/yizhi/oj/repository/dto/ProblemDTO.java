package com.yizhi.oj.repository.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yizhi.oj.repository.entity.Problem;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Zing
 * @Date: 2023/05/17/12:16
 * @Description:
 */
@Data
public class ProblemDTO{
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String title;

    private Integer type;

    @TableField("time_limit")
    private Integer timeLimit;

    @TableField("memory_limit")
    private Integer memoryLimit;

    @TableField("stack_limit")
    private Integer stackLimit;

    private String description;

    private String input;

    private String output;

    @TableField("input_examples")
    private String inputExamples;

    @TableField("output_examples")
    private String outputExamples;

    private Integer difficulty;

    private String hint;

    private Integer auth;

    @TableField("gmt_create")
    private Date gmtCreate;

    @TableField("gmt_modified")
    private Date gmtModified;
    private Long cpId;
}
