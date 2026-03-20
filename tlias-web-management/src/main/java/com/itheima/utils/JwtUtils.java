package com.itheima.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 令牌操作工具类
 */
public class JwtUtils {
    
    // 秘钥
    private static final String SECRET_KEY = "itheima";
    
    // 过期时间：12 小时（毫秒）
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000L;
    
    /**
     * 生成 JWT 令牌
     * @return JWT 令牌字符串
     */
    public static String generateToken(Map<String, Object>  claims) {


        return Jwts.builder()
                .setClaims(claims)           // 添加自定义数据
                .setIssuedAt(new Date())     // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 设置签名算法和秘钥
                .compact();                  // 生成令牌
    }
    
    /**
     * 解析 JWT 令牌
     * 
     * @param token JWT 令牌字符串
     * @return Claims 对象，包含所有自定义数据
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)   // 设置密钥
                .parseClaimsJws(token)       // 解析令牌
                .getBody();                  // 获取负载数据
    }
    
    /**
     * 从令牌中获取用户 ID
     * 
     * @param token JWT 令牌
     * @return 用户 ID
     */
    public static Integer getUserIdFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("id", Integer.class);
    }
    
    /**
     * 从令牌中获取用户名
     * 
     * @param token JWT 令牌
     * @return 用户名
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("username", String.class);
    }
    
    /**
     * 验证令牌是否过期
     * 
     * @param token JWT 令牌
     * @return true-未过期，false-已过期
     */
    public static boolean isTokenExpired(String token) {
        try {
            Claims claims = parseToken(token);
            Date expirationDate = claims.getExpiration();
            return expirationDate.before(new Date());
        } catch (Exception e) {
            return true; // 解析失败或过期都视为无效
        }
    }
    
    /**
     * 验证令牌是否有效
     * 
     * @param token JWT 令牌
     * @return true-有效，false-无效
     */
    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}
