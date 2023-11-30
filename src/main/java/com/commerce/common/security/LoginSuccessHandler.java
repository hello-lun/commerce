package com.commerce.common.security;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.commerce.entity.R;
import com.commerce.entity.SysMenu;
import com.commerce.entity.SysRole;
import com.commerce.entity.SysUser;
import com.commerce.service.RefreshTokensService;
import com.commerce.service.SysMenuService;
import com.commerce.service.SysRoleService;
import com.commerce.service.SysUserService;
import com.commerce.service.impl.RefreshTokensServiceImpl;
import com.commerce.util.JwtUtils;
import com.commerce.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 登录成功处理
 * @author commerce_小锋 （公众号：commerce）
 * @site www.commerce.vip
 * @company 南通小锋网络科技有限公司
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private RefreshTokensServiceImpl refreshTokensService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        String username=authentication.getName(); // 获取用户名
        sysUserService.update(new UpdateWrapper<SysUser>().set("login_date",new Date()).eq("username",username)); // 更新最后登录日期

        // 生成jwt token
        String token=JwtUtils.genJwtToken(username);

        SysUser currentUser = sysUserService.getByUserName(username);
        // 生成refreshToken，并且储存起来
        String refreshToken = refreshTokensService.savaRefleshToken(token, currentUser.getId());

        // 获取当前用户拥有的权限菜单
        // 获取角色
        List<SysRole> roleList = sysRoleService.list(new QueryWrapper<SysRole>().inSql("id", "select role_id from sys_user_role where user_id=" + currentUser.getId()));

        // 设置角色
        currentUser.setRoles(roleList.stream().map(SysRole::getName).collect(Collectors.joining(",")));

        StringBuffer permsStr=new StringBuffer();

        // 获取菜单权限
        Set<SysMenu> menuSet = new HashSet<SysMenu>();
        for(SysRole sysRole:roleList){
            List<SysMenu> sysMenuList = sysMenuService.list(new QueryWrapper<SysMenu>().inSql("id", "select menu_id from sys_role_menu where role_id=" + sysRole.getId()));
            for(SysMenu sysMenu:sysMenuList){
                menuSet.add(sysMenu);
                permsStr.append(sysMenu.getPerms()+",");
            }
        }

        String perms[]= StringUtils.tokenizeToStringArray(permsStr.toString(),",");

        List<SysMenu> sysMenuList = new ArrayList<>(menuSet); // 转成集合List

        sysMenuList.sort(Comparator.comparing(SysMenu::getOrderNum));  // 排序

        List<SysMenu> menuList = sysMenuService.buildTreeMenu(sysMenuList); // 构造菜单树
        R res = R.ok("登录成功");
        HashMap<String, Object> map = new HashMap();
        map.put("authorization",token);
        map.put("refreshToken",refreshToken);
        map.put("menuList",menuList);
        map.put("currentUser",currentUser);
        map.put("perms",perms);
        res.put("data",map);
        outputStream.write(JSONUtil.toJsonStr(res).getBytes("UTF-8"));

        outputStream.flush();
        outputStream.close();
    }



}
