package com.sinn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinn.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/6
 */
@Mapper
@Repository
public interface BlogMapper extends BaseMapper<Blog> {
}
