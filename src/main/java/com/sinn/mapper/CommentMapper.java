package com.sinn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinn.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/6
 */
@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
}
