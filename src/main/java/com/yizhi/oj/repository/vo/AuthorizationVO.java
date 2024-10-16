package com.yizhi.oj.repository.vo;

import lombok.Data;

/**
 * @Author: Zing
 * @Date: 2023/04/25/18:56
 * @Description:
 */
@Data
public class AuthorizationVO {
    private String accessToken;
    private Integer expiresIn;
}
