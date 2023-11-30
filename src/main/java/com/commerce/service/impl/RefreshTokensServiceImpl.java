package com.commerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.commerce.entity.CheckResult;
import com.commerce.entity.R;
import com.commerce.entity.RefreshTokens;
import com.commerce.service.RefreshTokensService;
import com.commerce.mapper.RefreshTokensMapper;
import com.commerce.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
* @author dinglun
* @description 针对表【refresh_tokens】的数据库操作Service实现
* @createDate 2023-11-27 17:48:38
*/
@Service
public class RefreshTokensServiceImpl extends ServiceImpl<RefreshTokensMapper, RefreshTokens>
    implements RefreshTokensService{
    private static final int REFRESH_TOKEN_VALIDITY_DAYS = 30; // 刷新令牌有效期为30天

    @Autowired
    RefreshTokensMapper refreshTokensMapper;

    public String valdRefleshToken(String refreshToken) {
        CheckResult checkResult = JwtUtils.validateJWT(refreshToken);
        if (checkResult.isSuccess()) {
            Claims claims = JwtUtils.parseJWT(refreshToken);
            String id = claims.getSubject();
            return JwtUtils.genJwtToken(id);
        }

        return null;
    }

    public String savaRefleshToken(String tokenId, Long userId) {
        LambdaQueryWrapper<RefreshTokens> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.eq(RefreshTokens::getUserId, userId);
        List<RefreshTokens> list = refreshTokensMapper.selectList(lambdaQuery);
        Long expiration = REFRESH_TOKEN_VALIDITY_DAYS * 24 * 60 * 60 * 1000L;
        String refreshToken = JwtUtils.createRefreshToken(userId, expiration);
        Date createdAt = new Date(System.currentTimeMillis());
        Date expirationTime = new Date(new Date().getTime() + expiration); // 当前时间加上30天
        if(list.isEmpty()) {
            RefreshTokens refreshTokens = new RefreshTokens();
            refreshTokens.setRefreshToken(refreshToken);
            refreshTokens.setTokenId(tokenId);
            refreshTokens.setCreatedAt(createdAt);
            refreshTokens.setUpdatedAt(createdAt);
            refreshTokens.setUserId(userId);
            refreshTokens.setExpirationTime(expirationTime);
            refreshTokensMapper.insert(refreshTokens);
        } else {
            for (RefreshTokens data : list) {
                data.setRefreshToken(refreshToken);
                data.setTokenId(tokenId);
                data.setCreatedAt(createdAt);
                data.setUpdatedAt(createdAt);
                data.setExpirationTime(expirationTime);
                refreshTokensMapper.updateById(data);
            }
        }
        return refreshToken;
    }
}




