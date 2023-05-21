package com.infs.blog.service.impl;

import com.infs.blog.mapper.ThemeMapper;
import com.infs.blog.model.Theme;
import com.infs.blog.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Lexi
 * @Date: 2023/05/10
 */

@Service
public class ThemeServiceImpl implements ThemeService {


    @Autowired
    private ThemeMapper themeMapper;

    /**
     * 查询所有主题
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<Theme> findThemeAll(Integer pageNo, Integer pageSize) {
        return themeMapper.findThemeAll(pageNo,pageSize);
    }
}
