package com.commerce.common.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * 自定义验证码异常
 * @author commerce_小锋 （公众号：commerce）
 * @site www.commerce.vip
 * @company 南通小锋网络科技有限公司
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg) {
        super(msg);
    }
}
