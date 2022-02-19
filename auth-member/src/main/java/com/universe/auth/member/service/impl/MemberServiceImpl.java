package com.universe.auth.member.service.impl;

import com.universe.auth.common.exception.AuthCommonException;
import com.universe.auth.common.exception.BizCodeEnum;
import com.universe.auth.common.model.member.MemberMongoEntity;
import com.universe.auth.member.service.MemberService;
import com.whaleal.icefrog.core.util.StrUtil;
import com.whaleal.mars.core.Mars;
import com.whaleal.mars.core.query.Criteria;
import com.whaleal.mars.core.query.Query;
import com.whaleal.mars.core.query.Update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description: MemberServiceImpl
 * @author: lhp
 * @time: 2022/2/9 2:33 下午
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private Mars mars;

    @Override
    public MemberMongoEntity login(String account, String password) {
        Criteria criteria = null;
        // 判断account类型
        if (account.startsWith("+")) {
            criteria = Criteria.where("phone").is(account);
        } else if (account.contains("@")) {
            criteria = Criteria.where("email").is(account);
        } else {
            criteria = Criteria.where("account").is(account);
        }
        // 查询此账号是否存在
        MemberMongoEntity memberMongoEntity = mars.findOne(new Query(criteria), MemberMongoEntity.class).orElse(null);
        if (memberMongoEntity == null) {
            throw new AuthCommonException(BizCodeEnum.NOT_EXIST_ACCOUNT);
        }
        // 校验密码是否正确
        String passwordDb = memberMongoEntity.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(password, passwordDb);
        if (!matches) {
            throw new AuthCommonException(BizCodeEnum.ERROR_PASSWORD);
        }
        return memberMongoEntity;
    }

    @Override
    public MemberMongoEntity save(MemberMongoEntity memberMongoEntity) {
        if (StrUtil.isBlank(memberMongoEntity.getAccount())) {
            throw new AuthCommonException(BizCodeEnum.BLANK_ACCOUNT);
        }
        // 查看手机号是否重复
        if (StrUtil.isNotBlank(memberMongoEntity.getPhone()) && checkPhoneDuplicate(memberMongoEntity.getPhone())) {
            throw new AuthCommonException(BizCodeEnum.EXIST_PHONE);
        }
        // 查看邮箱是否重复
        if (StrUtil.isNotBlank(memberMongoEntity.getEmail()) && checkEmailDuplicate(memberMongoEntity.getEmail())) {
            throw new AuthCommonException(BizCodeEnum.EXIST_EMAIL);
        }
        // 查看账号是否重复
        if (checkAccountDuplicate(memberMongoEntity.getAccount())) {
            throw new AuthCommonException(BizCodeEnum.EXIST_ACCOUNT);
        }
        Date date = new Date();
        // 密码加密存储
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberMongoEntity.setPassword(passwordEncoder.encode(memberMongoEntity.getPassword()));
        memberMongoEntity.setCreateTime(date);
        memberMongoEntity.setUpdateTime(date);
        return mars.save(memberMongoEntity);
    }

    @Override
    public MemberMongoEntity update(MemberMongoEntity memberMongoEntity) {
        // 更新时也需要考虑手机号 邮箱 是否重复
        if (memberMongoEntity.getId() == null) {
            throw new AuthCommonException(BizCodeEnum.NOT_EXIST_ACCOUNT);
        }
        Update update = new Update();
        // 查看手机号是否重复
        if (StrUtil.isNotBlank(memberMongoEntity.getPhone())) {
            MemberMongoEntity memberTemp = mars.findOne(Query.query(Criteria.where("phone").is(memberMongoEntity.getPhone())),
                    MemberMongoEntity.class).orElse(null);
            // 手机号为自己的则不用处理
            if (memberTemp != null && !memberTemp.getId().equals(memberMongoEntity.getId())) {
                throw new AuthCommonException(BizCodeEnum.EXIST_PHONE);
            }
            update.set("phone", memberMongoEntity.getPhone());
        }
        // 查看邮箱是否重复
        if (StrUtil.isNotBlank(memberMongoEntity.getEmail())) {
            MemberMongoEntity memberTemp = mars.findOne(Query.query(Criteria.where("email").is(memberMongoEntity.getEmail())),
                    MemberMongoEntity.class).orElse(null);
            // 邮箱号为自己的则不用处理
            if (memberTemp != null && !memberTemp.getId().equals(memberMongoEntity.getId())) {
                throw new AuthCommonException(BizCodeEnum.EXIST_EMAIL);
            }
            update.set("email", memberMongoEntity.getEmail());
        }
        if (StrUtil.isNotBlank(memberMongoEntity.getPassword())) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            update.set("password", passwordEncoder.encode(memberMongoEntity.getPassword()));
        }
        update.set("updateTime", new Date());
        Query query = new Query(Criteria.where("_id").is(memberMongoEntity.getId()));
        mars.update(query, update, MemberMongoEntity.class).getModifiedCount();
        return mars.findOne(Query.query(Criteria.where("_id").is(memberMongoEntity.getId())), MemberMongoEntity.class).orElse(null);
    }

    @Override
    public List<MemberMongoEntity> searchMember(String account, String email, String phone, int pageSize, int pageIndex) {
        Criteria criteria = Criteria.where("createTime").ne(null);
        if (StrUtil.isNotBlank(phone)) {
            criteria = criteria.and("phone").is(phone);
        }
        if (StrUtil.isNotBlank(email)) {
            criteria = criteria.and("email").is(email);
        }
        if (StrUtil.isNotBlank(account)) {
            criteria = criteria.and("account").is(account);
        }
        Query query = Query.query(criteria).skip((long) (pageIndex - 1) * pageSize).limit(pageSize);
        return mars.findAll(query, MemberMongoEntity.class).toList();
    }

    @Override
    public boolean checkAccountDuplicate(String account) {
        return mars.findOne(new Query(Criteria.where("account").is(account)), MemberMongoEntity.class).isPresent();
    }

    @Override
    public boolean checkEmailDuplicate(String email) {
        return mars.findOne(new Query(Criteria.where("email").is(email)), MemberMongoEntity.class).isPresent();
    }

    @Override
    public boolean checkPhoneDuplicate(String phone) {
        return mars.findOne(new Query(Criteria.where("phone").is(phone)), MemberMongoEntity.class).isPresent();
    }

}
