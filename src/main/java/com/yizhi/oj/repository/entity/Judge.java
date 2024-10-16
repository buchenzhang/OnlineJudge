package com.yizhi.oj.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Zing
 * @Date: 2023/04/09/11:47
 * @Description:
 */
@Data
@ApiModel("提交记录表")
public class Judge implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long pid;

    private String uid;

    private String username;

    private Date submitTime;

    private String status;

    @TableField("error_message")
    private String errorMessage;

    private Integer time;

    private Integer memory;

    private String language;

    private String code;

    private Double beyond;

    @TableField("submissionId")
    private String submissionId;
    // 0不是比赛提交 1是acm 2是oi
    @TableField("is_contest")
    private Integer isContest;
    private Long cid;

}
