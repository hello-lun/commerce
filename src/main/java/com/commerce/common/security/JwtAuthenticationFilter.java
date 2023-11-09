package com.commerce.common.security;

import com.commerce.common.constant.Constant;
import com.commerce.common.constant.JwtConstant;
import com.commerce.entity.CheckResult;
import com.commerce.entity.SysUser;
import com.commerce.service.SysUserService;
import com.commerce.util.JwtUtils;
import com.commerce.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author commerce_小锋 （公众号：commerce）
 * @site www.commerce.vip
 * @company 南通小锋网络科技有限公司
 */
public class JwtAuthenticationFilter  extends BasicAuthenticationFilter {

    @Autowired
    private MyUserDetailServiceImpl myUserDetailService;

    @Autowired
    private SysUserService sysUserService;

    private static final PathMatcher pathMatcher = new AntPathMatcher();

    private boolean isWhitelisted(String requestURI) {
        for (String pattern : Constant.URL_WHITELIST) {
            if (pathMatcher.match("/api" + pattern, requestURI)) {
                return true;
            }
        }
        return false;
    }

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("token");
        System.out.println("request.getRequestURI()"+request.getRequestURI());
        if(StringUtil.isEmpty(token) || isWhitelisted(request.getRequestURI())){
            chain.doFilter(request,response);
            return;
        }
        CheckResult checkResult = JwtUtils.validateJWT(token);
        if(!checkResult.isSuccess()){
            switch (checkResult.getErrCode()){
                case JwtConstant.JWT_ERRCODE_NULL: throw new JwtException("Token不存在");
                case JwtConstant.JWT_ERRCODE_FAIL: throw new JwtException("Token验证不通过");
                case JwtConstant.JWT_ERRCODE_EXPIRE: throw new JwtException("Token过期");
            }
        }


        Claims claims = JwtUtils.parseJWT(token);
        String username = claims.getSubject();

        SysUser sysUser = sysUserService.getByUserName(username);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username,null,myUserDetailService.getUserAuthority(sysUser.getId()));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        chain.doFilter(request,response);

    }
}
