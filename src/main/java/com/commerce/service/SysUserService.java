package com.commerce.service;

import com.commerce.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author commerce
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2022-06-22 08:45:09
*/
public interface SysUserService extends IService<SysUser> {

    SysUser getByUserName(String username);

    String getUserAuthorityInfo(Long userId);
}
