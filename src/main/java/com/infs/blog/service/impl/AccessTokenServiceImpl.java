package com.infs.blog.service.impl;

import com.infs.blog.mapper.AccessTokenMapper;
import com.infs.blog.model.AccessToken;
import com.infs.blog.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: Lexi
 * @Date: 2023/05/10
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService{

    @Autowired
    private AccessTokenMapper accessTokenMapper;

    @Override
    public AccessToken getByUserId(Integer userId) {
        return accessTokenMapper.selectByUserId(userId);
    }

    @Override
    public void save(AccessToken accessToken) {
        this.accessTokenMapper.insert(accessToken);
    }

    @Override
    public AccessToken create(String token, Integer userId) {
        AccessToken accessToken = new AccessToken();
        accessToken.setToken(token);
        accessToken.setUserId(userId);
        accessToken.setCreateDate(new Date());
        this.save(accessToken);
        return accessToken;
    }
}
