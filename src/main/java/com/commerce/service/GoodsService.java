package com.commerce.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.commerce.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author dinglun
* @description 针对表【goods】的数据库操作Service
* @createDate 2023-11-05 03:20:14
*/
public interface GoodsService extends IService<Goods> {
    public IPage<Goods> getGoodsPagation(Page<Goods> page, String category);
}
