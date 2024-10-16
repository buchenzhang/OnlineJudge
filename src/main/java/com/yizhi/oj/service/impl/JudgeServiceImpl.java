package com.yizhi.oj.service.impl;

import com.yizhi.oj.repository.dto.AuthorizationDTO;
import com.yizhi.oj.repository.dto.JudgeDTO;
import com.yizhi.oj.repository.vo.AuthorizationVO;
import com.yizhi.oj.repository.vo.JudgeConditionVO;
import com.yizhi.oj.service.JudgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Zing
 * @Date: 2023/04/25/18:50
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JudgeServiceImpl implements JudgeService {

    private final RestTemplate restTemplate;

    private static final String AUTHURL = "http://47.92.52.220:8080/auth/access_token";
    private static final String JUDGEURL = "http://47.92.52.220:8080/judge/result";
    private static final String userId = "yzl";
    private static final String userSecret = "yzl";


    @Override
    public AuthorizationVO getAuth() {
        AuthorizationDTO authorizationDTO = new AuthorizationDTO();
        authorizationDTO.setUserId(userId);
        authorizationDTO.setUserSecret(userSecret);
        return restTemplate.postForObject(AUTHURL, authorizationDTO, AuthorizationVO.class);
    }

    @Override
    public JudgeConditionVO judge(JudgeDTO judgeDTO) {
        AuthorizationVO authorizationVO = getAuth();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("accessToken",authorizationVO.getAccessToken());
        HttpEntity requestEntity = new HttpEntity<>(judgeDTO,requestHeaders);
        return restTemplate.postForObject(JUDGEURL,requestEntity,JudgeConditionVO.class);
    }
}
