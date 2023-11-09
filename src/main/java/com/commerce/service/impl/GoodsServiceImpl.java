package com.commerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.commerce.entity.Goods;
import com.commerce.service.GoodsService;
import com.commerce.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author dinglun
* @description 针对表【goods】的数据库操作Service实现
* @createDate 2023-11-05 03:20:14
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

    @Autowired
    GoodsMapper goodsMapper;
    public IPage<Goods> getGoodsPagation(Page<Goods> page, String category) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (category != null && !category.isEmpty()) {
            queryWrapper.eq("type", category);
        }
        return goodsMapper.selectPage(page, queryWrapper);
    }
}




