package com.commerce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.commerce.entity.Articals;
import com.commerce.service.ArticalsService;
import com.commerce.mapper.ArticalsMapper;
import org.springframework.stereotype.Service;

/**
* @author dinglun
* @description 针对表【articals】的数据库操作Service实现
* @createDate 2023-10-29 03:05:39
*/
@Service
public class ArticalsServiceImpl extends ServiceImpl<ArticalsMapper, Articals>
    implements ArticalsService{
}




