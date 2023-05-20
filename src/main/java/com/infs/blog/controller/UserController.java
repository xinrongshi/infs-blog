package com.infs.blog.controller;

import com.infs.blog.bean.Page;
import com.infs.blog.bean.Result;
import com.infs.blog.conf.properties.SiteConfig;
import com.infs.blog.exception.ApiAssert;
import com.infs.blog.model.Article;
import com.infs.blog.model.User;
import com.infs.blog.service.ArticleService;
import com.infs.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Lexi
 * @Date: 2023/05/10
 */


@RestController
public class UserController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private SiteConfig siteConfig;

    /**
     * 根据作者获取文章
     * @param username
     * @param pageNo
     * @return
     */
    @GetMapping(value = "/user/articles")
    private Result userArticles(@RequestParam(value = "username") String username,
                                @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo){
        ApiAssert.notNull(username,"作者名称不能为空");
        Page<Article> page = articleService.pageByAuthor(pageNo, siteConfig.getPageSize(), username);
        return Result.success(page);
    }

    /**
     * 根据用户昵称获取用户数据
     * @param username
     * @return
     */

    @GetMapping(value = "/users/{username}")
    private Result userDetail(@PathVariable(value = "username") String username){
        ApiAssert.notNull(username,"作者名称不能为空");
        User user = userService.findUserByName(username);
        ApiAssert.notNull(user,"用户不存在");
        return Result.success(user);
    }

    /**
     * 推荐作者
     * @return
     */
    @GetMapping(value = "/user/recommend")
    private Result recommend(){
        List<User> list = userService.findUserByArticleCountDesc(0, 5);
        return Result.success(list);
    }
}
