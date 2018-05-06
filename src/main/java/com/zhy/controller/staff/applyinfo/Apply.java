package com.zhy.controller.staff.applyinfo;

import com.zhy.service.mybatis.StaffOutsourcingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * @author: zhangocean
 * @Date: 2018/4/13 13:40
 * Describe:
 */
@Controller
@RequestMapping("/apply")
public class Apply {

    @Autowired
    StaffOutsourcingService staffOutsourcingService;

    @PostMapping("/applyForOutsourcing")
    @ResponseBody
    public int applyForOutsourcing(@RequestParam String id, @AuthenticationPrincipal Principal principal){

        int applyResult = staffOutsourcingService.applyForOutsourcing(Integer.parseInt(id), principal.getName());
        System.out.println("applyResult is " + applyResult);

        return applyResult;

    }

}
