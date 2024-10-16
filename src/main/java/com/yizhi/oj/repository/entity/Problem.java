package com.yizhi.oj.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Zing
 * @Date: 2023/04/08/19:29
 * @Description:
 */
@Data
public class Problem implements Serializable {

    private static final long serialVersionUID = 1L;

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

}
