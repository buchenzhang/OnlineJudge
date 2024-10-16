package com.yizhi.oj.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Zing
 * @Date: 2023/05/01/14:20
 * @Description:
 */
@Data
public class ContestList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private Long cid;
    private String uid;
    private String username;
    private Integer solved;
    private Integer time;

}
