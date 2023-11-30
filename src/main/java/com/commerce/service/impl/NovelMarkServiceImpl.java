package com.commerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.commerce.entity.Goods;
import com.commerce.entity.NewWord;
import com.commerce.entity.NovelMark;
import com.commerce.mapper.GoodsMapper;
import com.commerce.mapper.NewWordMapper;
import com.commerce.service.NovelMarkService;
import com.commerce.mapper.NovelMarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author dinglun
* @description 针对表【novel_mark】的数据库操作Service实现
* @createDate 2023-10-29 03:06:02
*/
@Service
public class NovelMarkServiceImpl extends ServiceImpl<NovelMarkMapper, NovelMark>
    implements NovelMarkService{

}




