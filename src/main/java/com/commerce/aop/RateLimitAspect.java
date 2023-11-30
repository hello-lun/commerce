package com.commerce.aop;

import com.commerce.annotations.RateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class RateLimitAspect {

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    @Pointcut("@annotation(rateLimit)")
    public void rateLimitPointcut(RateLimit rateLimit) {
    }

    @Around("rateLimitPointcut(rateLimit)")
    public Object rateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        String key = "rateLimit:" + joinPoint.getSignature().toShortString() + ":" + ip;

        ValueOperations<String, Integer> valueOps = redisTemplate.opsForValue();
        Long currentCount = valueOps.increment(key);
        if (currentCount == 1) {
            redisTemplate.expire(key, rateLimit.timeout(), TimeUnit.SECONDS);
        }

        if (currentCount != null && currentCount > rateLimit.limit()) {
            throw new RuntimeException("Too many requests from IP: " + ip);
        }

        return joinPoint.proceed();

    }
}
