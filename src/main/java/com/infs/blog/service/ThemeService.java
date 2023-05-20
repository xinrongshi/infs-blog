package com.infs.blog.service;

import com.infs.blog.model.Theme;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Lexi
 * @Date: 2023/05/10
 */
@Service
public interface ThemeService {

    /**
     * 查询所有主题
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Theme> findThemeAll(Integer pageNo, Integer pageSize);

}
