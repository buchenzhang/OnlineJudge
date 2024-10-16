package com.yizhi.oj.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: Zing
 * @Date: 2023/03/19/19:35
 * @Description:
 */
@Data
@ApiModel("用户模型")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty("学号")
    @TableId(value = "id",type = IdType.INPUT)
    private String id;

    @ApiModelProperty("昵称")
    @TableField("username")
    private String username;

    @ApiModelProperty("性别")
    @TableField("sex")
    private Boolean sex;

    @ApiModelProperty("学院")
    @TableField("academy")
    private String academy;

    @ApiModelProperty("专业")
    @TableField("specialty")
    private String specialty;

    @ApiModelProperty("毕业年份")
    @TableField("graduate_year")
    private Integer graduateYear;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("个人简介")
    @TableField("synopsis")
    private String synopsis;

    @ApiModelProperty("头像地址")
    @TableField("headshot")
    private String headshot;

    @ApiModelProperty("是否认证 true是false否")
    @TableField("is_attestation")
    private Boolean isAttestation;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @TableField("role")
    private String role;

}
