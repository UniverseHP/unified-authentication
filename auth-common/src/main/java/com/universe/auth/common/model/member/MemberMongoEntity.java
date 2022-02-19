package com.universe.auth.common.model.member;

import com.universe.auth.common.model.BaseMongoEntity;
import com.universe.auth.common.model.Info;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
     * 头像url
     */
    private String profile;
    /**
     * 昵称
     */
    private String nickName;
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
     * 格式 +86_17856923766
     */
    private String phone;
    /**
     * 所在组织列表
     */
    private List<Info> organizationInfoList;
    /**
     * 所在团队列表
     */
    private List<Info> teamInfoList;
}
