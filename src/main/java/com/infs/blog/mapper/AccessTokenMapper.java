package com.infs.blog.mapper;

import com.infs.blog.model.AccessToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Lexi
 * @Date: 2023/05/15
 */
@Mapper
public interface AccessTokenMapper {

    AccessToken selectByUserId(@Param("userId") Integer UserId);

    int insert(AccessToken accessToken);
}
