package com.itheima;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");
        String jwt = Jwts.builder() // 创建jwt构建器
                .signWith(SignatureAlgorithm.HS256, "itheima") // 生成加密算法和秘钥
                .addClaims(dataMap)// 添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置过期时间
                .compact(); // 生成jwt
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc3Mzk3NzY2Mn0.OGPqf7xvU1FTv4XaAzO50xH-rKFmE12wG8NksE4KOrs";
        Map<String, Object> claims = Jwts.parser()
                .setSigningKey("itheima") // 设置密钥
                .parseClaimsJws(jwt) // 解析jwt
                .getBody(); // 获取自定义数据
        System.out.println(claims);
    }
}
