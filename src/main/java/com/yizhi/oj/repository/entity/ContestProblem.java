package com.yizhi.oj.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Zing
 * @Date: 2023/04/12/15:11
 * @Description:
 */
@Data
public class ContestProblem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private Long cid;
    private Long pid;
    private String title;
    private String number;
}
