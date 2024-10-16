package com.yizhi.oj.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Zing
 * @Date: 2023/04/18/12:37
 * @Description:
 */
@Data
@TableName("feedback")
public class FeedBack implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String uid;
    private String username;
    @TableField("date_time")
    private Date dateTime;
    private String text;
    private String phone;
    private Integer state;
}
