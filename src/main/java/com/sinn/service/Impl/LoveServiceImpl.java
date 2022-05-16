package com.sinn.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinn.mapper.LoveMapper;
import com.sinn.pojo.Love;
import com.sinn.service.LoveService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/16
 */
@Service
public class LoveServiceImpl extends ServiceImpl<LoveMapper, Love> implements LoveService {
}
