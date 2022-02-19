package com.universe.auth.common.helper;

import com.universe.auth.common.model.member.MemberMongoEntity;
import io.jsonwebtoken.*;


import java.util.Date;


/**
 * JWT处理类
 *
 * @author mlhp1
 */
public class JwtHelper {

    /**
     * 24小时有效期
     */
    private static final long TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;
    /**
     * 密钥
     */
    private static final String TOKEN_SIGN_KEY = "WoW,AUTH!";

    /**
     * 创建用户Token
     *
     * @param memberMongoEntity 用户实体对象
     * @return token 加密后的字符串
     */
    public static String createMemberToken(MemberMongoEntity memberMongoEntity) {
        String token = Jwts.builder()
                .setSubject("AUTH-MEMBER")
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .claim("name", memberMongoEntity.getAccount())
                .claim("id", memberMongoEntity.getId())
                .claim("email", memberMongoEntity.getEmail())
                .claim("phone", memberMongoEntity.getPhone())
                .signWith(SignatureAlgorithm.HS512, TOKEN_SIGN_KEY)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }


    public static void main(String[] args) {
    }
}
