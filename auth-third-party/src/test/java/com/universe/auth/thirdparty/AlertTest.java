package com.universe.auth.thirdparty;

import com.universe.auth.thirdparty.service.DingDingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @user Lyz
 * @description
 * @date 2022/1/14 10:37
 */
@SpringBootTest(classes = AuthThirdPartyApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AlertTest {

    @Autowired
    private DingDingService dingDingService;

    @Test
    public void testForDingDing(){
//        List<String> list = new ArrayList<>();
//        list.add("15617339317");
//        dingDingService.sendAlertMsg(false,list);
    }
}
