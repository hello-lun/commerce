package com.commerce.service;

import com.commerce.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author commerce
* @description 针对表【sys_menu】的数据库操作Service
* @createDate 2022-07-05 08:48:37
*/
public interface SysMenuService extends IService<SysMenu> {

     public List<SysMenu> buildTreeMenu(List<SysMenu> sysMenuList);

}
