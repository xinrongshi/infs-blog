package com.infs.blog.service;

import com.infs.blog.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Lexi
 * @Date: 2023/05/10
 */
@Service
public interface UserService {

    /**
     * 根据昵称查询用户
     * @param name
     * @return
     */
    User findUserByName(String name);

    /**
     * 发表文章数量最多的用户
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<User> findUserByArticleCountDesc(Integer pageNo, Integer pageSize);

    void save(User user);

    User create(String username, String password, String email);
}
