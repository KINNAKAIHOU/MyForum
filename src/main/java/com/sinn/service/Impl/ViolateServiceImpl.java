package com.sinn.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinn.mapper.ViolateMapper;
import com.sinn.pojo.Violate;
import com.sinn.service.ViolateService;
import org.springframework.stereotype.Service;

@Service
public class ViolateServiceImpl extends ServiceImpl<ViolateMapper, Violate> implements ViolateService {
}
