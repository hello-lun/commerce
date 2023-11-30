package com.commerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.commerce.entity.Goods;
import com.commerce.entity.NewWord;
import com.commerce.service.NewWordService;
import com.commerce.mapper.NewWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author dinglun
* @description 针对表【new_word】的数据库操作Service实现
* @createDate 2023-11-23 22:28:24
*/
@Service
public class NewWordServiceImpl extends ServiceImpl<NewWordMapper, NewWord>
    implements NewWordService{
    @Autowired
    NewWordMapper newWordMapper;
    public IPage<NewWord> getNewWordPagation(Page<NewWord> page, int isRead) {
        QueryWrapper<NewWord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isRead", isRead); // 添加筛选条件
        return newWordMapper.selectPage(page, queryWrapper);
    }
}




