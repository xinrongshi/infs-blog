package com.infs.blog.controller;

import com.infs.blog.bean.Page;
import com.infs.blog.bean.Result;
import com.infs.blog.conf.properties.SiteConfig;
import com.infs.blog.exception.ApiAssert;
import com.infs.blog.model.Article;
import com.infs.blog.model.Theme;
import com.infs.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Lexi
 * @Date: 2023/05/10
 */
@RestController
public class ThemeController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private com.infs.blog.service.ThemeService themeService;

    @Autowired
    private SiteConfig siteConfig;

    /**
     * 根据主题名称查询文章
     * @param themeName: 主题名称
     * @param pageNo: 当前页
     * @return
     */
    @GetMapping(value = "/theme/articles")
    private Result detail(@RequestParam(value = "themeName") String themeName,
                          @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo){
        ApiAssert.notNull(themeName,"Subject name cannot be empty");
        Page<Article> page = articleService.pageByThemeName(pageNo, siteConfig.getPageSize(), themeName);
        return Result.success(page);
    }

    /**
     * 获取所有的主题
     * @return
     */
    @GetMapping(value = "/themes")
    private Result themeList(){
        List<Theme> list = themeService.findThemeAll(null, null);
        return Result.success(list);
    }
}
