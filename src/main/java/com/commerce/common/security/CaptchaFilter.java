package com.commerce.common.security;


import com.commerce.common.constant.Constant;
import com.commerce.common.exception.CaptchaException;
import com.commerce.util.RedisUtil;
import com.commerce.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 * @author commerce_小锋 （公众号：commerce）
 * @site www.commerce.vip
 * @company 南通小锋网络科技有限公司
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String url = httpServletRequest.getRequestURI();

        if ("/login".equals(url) && httpServletRequest.getMethod().equals("POST")) {

            try{
                // 校验验证码
                validate(httpServletRequest);
            } catch (CaptchaException e) {

                // 交给认证失败处理器
                loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    // 校验验证码逻辑
    private void validate(HttpServletRequest httpServletRequest) {

        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("uuid");

        if (StringUtil.isEmpty(code) || StringUtil.isEmpty(key)) {
            throw new CaptchaException("验证码错误");
        }

        if (!code.equals(redisUtil.hget(Constant.CAPTCHA_KEY, key))) {
            throw new CaptchaException("验证码错误");
        }

    }
}
