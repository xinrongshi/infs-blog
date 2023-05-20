package com.infs.blog.mapper;

import com.infs.blog.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Lexi
 * @Date: 2023/05/15
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户昵称查询用户
     * @param name
     * @return
     */
    User findUserByName(@Param("name") String name);

    /**
     * 查询发表文章数量最多的用户
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<User> findUserByArticleCountDesc(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    int insert(User user);
}
