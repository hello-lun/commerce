package com.commerce.common.security;

import cn.hutool.json.JSONUtil;
import com.commerce.entity.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt认证异常处理
 * @author commerce_小锋 （公众号：commerce）
 * @site www.commerce.vip
 * @company 南通小锋网络科技有限公司
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        // 构建响应内容
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("error", HttpServletResponse.SC_UNAUTHORIZED);
        responseData.put("message", "认证失败，请登录！");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        outputStream.write(JSONUtil.toJsonStr(responseData).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}
