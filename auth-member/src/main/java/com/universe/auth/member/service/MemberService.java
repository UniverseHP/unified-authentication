package com.universe.auth.member.service;


import com.universe.auth.common.model.member.MemberMongoEntity;

import java.util.List;

/**
 * memberService.
 *
 * @description:
 * @author: lhp
 * @time: 2022 /2/9 2:32 下午
 */
public interface MemberService {


    /**
     * 登录.
     *
     * @param account  账号/手机号/邮箱
     * @param password 密码/
     * @return member实体对象
     */
    MemberMongoEntity login(String account, String password);

    /**
     * 保存新用户信息.
     *
     * @param memberMongoEntity member实体对象
     * @return member实体对象
     */
    MemberMongoEntity save(MemberMongoEntity memberMongoEntity);

    /**
     * 更新用户信息.
     * 此方法 只更新 手机号 邮箱 昵称 信息。
     * @param memberMongoEntity member实体对象
     * @return member实体对象
     */
    MemberMongoEntity update(MemberMongoEntity memberMongoEntity);

    /**
     * 搜索用户.
     *
     * @param account   账号
     * @param email     邮箱
     * @param phone     手机
     * @param pageSize  每页大小
     * @param pageIndex 页标签
     * @return the list
     */
    List<MemberMongoEntity> searchMember(String account, String email, String phone, int pageSize, int pageIndex);

    /**
     * 检查账号是否重复.
     *
     * @param account 账号
     * @return the boolean
     */
    boolean checkAccountDuplicate(String account);

    /**
     * 检查邮箱是否重复
     *
     * @param email 邮箱
     * @return the boolean
     */
    boolean checkEmailDuplicate(String email);

    /**
     * 检查手机号是否重复
     *
     * @param phone 手机号
     * @return the boolean
     */
    boolean checkPhoneDuplicate(String phone);
}
