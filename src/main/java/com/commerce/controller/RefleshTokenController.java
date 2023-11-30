package com.commerce.controller;


import com.commerce.entity.CheckResult;
import com.commerce.entity.R;
import com.commerce.service.impl.RefreshTokensServiceImpl;
import com.commerce.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RefleshTokenController {
    @Autowired
    RefreshTokensServiceImpl refreshTokensService;

    @GetMapping("/refresh-token")
    public ResponseEntity<R> refreshToken(@RequestParam String refreshToken) {
        String token = refreshTokensService.valdRefleshToken(refreshToken);
        if (token != null) {
            R res = R.ok();
            res.put("data", token);
            return ResponseEntity.ok(res);
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 设置状态码为400
                .body(R.error(400, "refreshToken验证失败！"));
    }

}
