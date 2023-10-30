package com.commerce.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 用户账号封禁异常
 * @author commerce_小锋 （公众号：commerce）
 * @site www.commerce.vip
 * @company 南通小锋网络科技有限公司
 */
public class UserCountLockException extends AuthenticationException {

    public UserCountLockException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserCountLockException(String msg) {
        super(msg);
    }
}
