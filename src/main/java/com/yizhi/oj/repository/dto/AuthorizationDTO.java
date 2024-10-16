package com.yizhi.oj.repository.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: Zing
 * @Date: 2023/04/25/18:53
 * @Description:
 */
@Data
public class AuthorizationDTO {
    private String userId;
    private String userSecret;
}
