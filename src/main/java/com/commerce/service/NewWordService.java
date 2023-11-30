package com.commerce.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.commerce.entity.Goods;
import com.commerce.entity.NewWord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dinglun
* @description 针对表【new_word】的数据库操作Service
* @createDate 2023-11-23 22:28:24
*/
public interface NewWordService extends IService<NewWord> {
    public IPage<NewWord> getNewWordPagation(Page<NewWord> page, int isRead);
}
