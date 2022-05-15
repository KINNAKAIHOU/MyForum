package com.sinn.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinn.mapper.FavoriteMapper;
import com.sinn.pojo.Favorite;
import com.sinn.service.FavoriteService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/5/15
 */
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {
}
