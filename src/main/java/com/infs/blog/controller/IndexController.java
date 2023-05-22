package com.infs.blog.controller;

import com.infs.blog.bean.Page;
import com.infs.blog.conf.properties.SiteConfig;
import com.infs.blog.bean.Result;
import com.infs.blog.exception.ApiAssert;
import com.infs.blog.model.AccessToken;
import com.infs.blog.model.Article;
import com.infs.blog.model.User;
import com.infs.blog.service.AccessTokenService;
import com.infs.blog.service.ArticleService;
import com.infs.blog.service.ThemeService;
import com.infs.blog.service.UserService;
import com.infs.blog.util.JwtTokenUtil;
import com.infs.blog.util.StringUtil;
import com.infs.blog.util.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: Lexi
 * @Date: 2023/05/10
 */
@RestController
public class IndexController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccessTokenService accessTokenService;
    @Autowired
    private ThemeService themeService;
    @Autowired
    private SiteConfig siteConfig;
    @Autowired
    private StringUtil stringUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @GetMapping(value = "/articles")
    private Result articles(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo){
        Page<Article> page = articleService.page(pageNo, siteConfig.getPageSize());
        return Result.success(page);
    }

    /**
     * 热门文章
     * @return
     */
    @GetMapping(value = "/articles/hot")
    private Result hotNews(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                           @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        Page<Article> page = articleService.pageByWeight(pageNo, pageSize);
        return Result.success(page);
    }

    @PostMapping(value = "/register")
    public Result register(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        ApiAssert.notEmpty(username, "请输入用户名");
        ApiAssert.notEmpty(password, "请输入密码");
        ApiAssert.isTrue(stringUtil.check(username,stringUtil.usernameRegex),"用户名只能输入[0-9a-zA-Z]，长度4-16位");
        ApiAssert.isTrue(stringUtil.check(password,stringUtil.passwordRegex),"密码只能输入[0-9a-zA-Z]，长度6-32位");
        user = userService.findUserByName(username);
        ApiAssert.isNull(user,"用户名已经存在");
        // 保存用户
        user = userService.create(username, password, email);
        // 保存Token
        AccessToken accessToken = accessTokenService.create(jwtTokenUtil.generateToken(user.getUsername()), user.getUserId());
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("avatar",user.getAvatar());
        map.put("token",accessToken.getToken());
        return Result.success(map);
    }

    @PostMapping(value = "/login")
    public Result login(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        ApiAssert.notEmpty(username, "请输入用户名");
        ApiAssert.notEmpty(password, "请输入密码");
        User user1 = userService.findUserByName(username);
        ApiAssert.notNull(user, "用户不存在");
        ApiAssert.isTrue(!user1.getPassword().equals(password), "密码不正确");
        AccessToken accessToken = accessTokenService.getByUserId(user.getUserId());
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("avatar",user.getAvatar());
        map.put("token",accessToken.getToken());
        return Result.success(map);
    }
}
