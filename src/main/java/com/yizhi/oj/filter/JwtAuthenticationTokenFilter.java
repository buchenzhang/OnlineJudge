package com.yizhi.oj.filter;

import com.alibaba.fastjson.JSONObject;
import com.yizhi.oj.common.JWTUtil;
import com.yizhi.oj.repository.dto.LoginUser;
import com.yizhi.oj.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: Zing
 * @Date: 2023/03/19/21:06
 * @Description:
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final RedisTemplate redisTemplate;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug(request.getRequestURI());
        String token = request.getHeader("Authorization");
        if (token == null){
            filterChain.doFilter(request, response);
            return;
        }

        if (checkToken(token) != null){
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            filterChain.doFilter(request, response);
            return;
        }
        //从redis中获取用户信息
        String redisKey = "login_" + JWTUtil.getId(token);
        Object obj = redisTemplate.opsForValue().get(redisKey);
        LoginUser loginUser = JSONObject.parseObject((String) obj, LoginUser.class);
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }

        //封装Authentication对象存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication中

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

    /**
     * 返回null为token校验成功 失败返回错误信息
     * @param token
     * @return
     */
    public String checkToken(String token){
        if (token == null) {
            return "NOT LOGIN";
        }
        //校验token
        JWTUtil.validateToken(token);
        if (redisTemplate.opsForValue().get("token_" + JWTUtil.getId(token)) == null){
            return "TOKEN EXPIRED";
        }
        return null;
    }
}
