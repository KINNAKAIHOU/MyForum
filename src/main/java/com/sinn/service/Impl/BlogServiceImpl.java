package com.sinn.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinn.mapper.BlogMapper;
import com.sinn.pojo.Blog;
import com.sinn.service.BlogService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/6
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
}
