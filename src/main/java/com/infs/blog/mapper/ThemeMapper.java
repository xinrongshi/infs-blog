package com.infs.blog.mapper;

import com.infs.blog.model.Theme;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @Author: Lexi
 * @Date: 2023/05/15
 */
@Mapper
public interface ThemeMapper {

    /**
     * 查询所有主题
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Theme> findThemeAll(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
}
