package com.yizhi.oj.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Zing
 * @Date: 2023/04/12/14:46
 * @Description:
 */
@Data
public class Contest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String uid;

    private String author;
    private String title;
    private String type;
    private Integer auth;
    private String pwd;
    @TableField("start_time")
    private Date startTime;
    @TableField("end_time")
    private Date endTime;
    private Long duration;
    private String description;

}
