package com.sinn.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinn.mapper.CommentMapper;
import com.sinn.pojo.Comment;
import com.sinn.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/6
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}
