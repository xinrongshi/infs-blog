package com.infs.blog.service.impl;

import com.infs.blog.mapper.UserMapper;
import com.infs.blog.model.User;
import com.infs.blog.service.UserService;
import com.infs.blog.util.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Lexi
 * @Date: 2023/05/10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据昵称查询用户
     * @param name: 用户昵称
     * @return
     */
    @Override
    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    /**
     * 查询发表文章数量最多的用户
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<User> findUserByArticleCountDesc(Integer pageNo, Integer pageSize) {
        return userMapper.findUserByArticleCountDesc(pageNo,pageSize);
    }

    @Override
    public void save(User user) {
        this.userMapper.insert(user);
    }

    @Override
    public User create(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setAvatar("https://wx4.sinaimg.cn/large/b8fbd005gy1gxicusldztj20i20i2glv.jpg");
        user.setSignature("这家伙很懒，什么都没留下");
        user.setUserUrl("/user/"+username);
        user.setScore(0);
        user.setWebsite(null);
        user.setBlock(false);
        user.setDelete(false);
        user.setRole(null);
        user.setCreateDate(new Date());
        user.setUpdateDate(null);
        user.setRemark(null);
        this.save(user);
        return user;
    }
}
