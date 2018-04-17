package com.zhy.controller.staff.sign;

import com.zhy.service.mybatis.SignStateService;
import com.zhy.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

/**
 * @author: zhangocean
 * @Date: 2018/4/16 14:15
 * Describe:
 */
@Controller
@RequestMapping("/sign")
public class StaffSign {

    @Autowired
    SignStateService signStateService;

    @PostMapping("/signIn")
    @ResponseBody
    public int signIn(@AuthenticationPrincipal Principal principal,
                       @RequestParam("signInDate") String signInDate){

        TimeUtil timeUtil = new TimeUtil();
        String time = timeUtil.longToSixStringTime(System.currentTimeMillis());
        System.out.println("签到时间为：" + time);
        return signStateService.updateState(principal.getName(), time);

    }

    @PostMapping("/signOut")
    @ResponseBody
    public int signOut(@AuthenticationPrincipal Principal principal,
                       @RequestParam("signOutDate") String signOutDate){

        TimeUtil timeUtil = new TimeUtil();
        String time = timeUtil.longToSixStringTime(System.currentTimeMillis());
        System.out.println("签退时间为：" + time);
        return signStateService.updateState(principal.getName(), time);

    }

    @GetMapping("/getSignState")
    @ResponseBody
    public int getSignState(@AuthenticationPrincipal Principal principal){

        return signStateService.getSignState(principal.getName());

    }

}
