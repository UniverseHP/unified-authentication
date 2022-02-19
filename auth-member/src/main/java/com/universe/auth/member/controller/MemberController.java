package com.universe.auth.member.controller;

import com.universe.auth.common.exception.BizCodeEnum;
import com.universe.auth.common.helper.JwtHelper;
import com.universe.auth.common.model.member.MemberMongoEntity;
import com.universe.auth.common.utils.R;
import com.universe.auth.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户信息Controller
 *
 * @desc: 用户信息Controller
 * @author: lhp
 * @time: 2022 /2/9 2:29 下午
 */
@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 登录.
     *
     * @param map map
     * @return member实体对象
     */
    @PostMapping("/login")
    public R login(@RequestBody Map<String, String> map) {
        String account = map.get("account");
        String password = map.get("password");
        MemberMongoEntity memberMongoEntity = memberService.login(account, password);
        if (memberMongoEntity == null) {
            return R.noExpect(BizCodeEnum.ERROR_PASSWORD);
        }
        // 可以保存token
        memberMongoEntity.setPassword(null);
        memberMongoEntity.setUpdateTime(null);
        return R.ok().put("data", memberMongoEntity).put("token", JwtHelper.createMemberToken(memberMongoEntity));
    }

    /**
     * 保存新用户信息.
     *
     * @param memberMongoEntity member实体对象
     * @return member实体对象
     */
    @PostMapping("/register")
    public R register(@RequestBody MemberMongoEntity memberMongoEntity) {
        MemberMongoEntity mongoEntity = memberService.save(memberMongoEntity);
        memberMongoEntity.setPassword(null);
        return R.ok().put("data", mongoEntity);
    }

    /**
     * 更新用户信息.
     *
     * @param memberMongoEntity member实体对象
     * @return member实体对象
     */
    @PostMapping("/update")
    public R update(@RequestBody MemberMongoEntity memberMongoEntity) {
        MemberMongoEntity mongoEntity = memberService.update(memberMongoEntity);
        if (mongoEntity == null) {
            return R.noExpect(BizCodeEnum.ERROR_UPDATE_MEMBER);
        }
        memberMongoEntity.setPassword(null);
        return R.ok().put("data", mongoEntity);
    }

    /**
     * 搜索用户.
     *
     * @param map       map
     * @param pageSize  每页大小
     * @param pageIndex 页标签
     * @return the list
     */
    @PostMapping("/search/{pageSize}/{pageIndex}")
    public R search(@RequestBody Map<String, String> map, @PathVariable("pageSize") int pageSize, @PathVariable("pageIndex") int pageIndex) {
        String account = map.get("account");
        String phone = map.get("phone");
        String email = map.get("email");
        return R.ok().put("data", memberService.searchMember(account, email, phone, pageSize, pageIndex));
    }
}
