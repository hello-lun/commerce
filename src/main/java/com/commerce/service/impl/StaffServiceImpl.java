package com.commerce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.commerce.entity.Staff;
import com.commerce.service.StaffService;
import com.commerce.mapper.StaffMapper;
import org.springframework.stereotype.Service;

/**
* @author dinglun
* @description 针对表【staff】的数据库操作Service实现
* @createDate 2023-11-08 23:19:34
*/
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff>
    implements StaffService{

}




