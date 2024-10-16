package com.yizhi.oj.repository.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.yizhi.oj.repository.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Zing
 * @Date: 2023/03/19/20:15
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginUser implements UserDetails {

    private User user;

    /**
     * 存放当前登录用户的权限信息，一个用户可以有多个权限
     */
    private List<String> permissions;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    //权限集合
    @JSONField(serialize = false)
    private  List<SimpleGrantedAuthority>  authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null){
            return authorities;
        }

        authorities = permissions.stream().
                map(SimpleGrantedAuthority::new).
                collect(Collectors.toList());

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
