package com.infs.blog.service;

import com.infs.blog.model.AccessToken;
import org.springframework.stereotype.Service;

/**
 * @Author: Lexi
 * @Date: 2023/05/10
 */
public interface AccessTokenService {

    AccessToken getByUserId(Integer userId);

    void save(AccessToken accessToken);

    AccessToken create(String token,Integer userId);
}
