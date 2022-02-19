package com.universe.auth.common.model.member;

import com.universe.auth.common.model.BaseMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户信息实体类
 * @author: lhp
 * @time: 2022/2/9 2:26 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMongoEntity extends BaseMongoEntity {
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
}
