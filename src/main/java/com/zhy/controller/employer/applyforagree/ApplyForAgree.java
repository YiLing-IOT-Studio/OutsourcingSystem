package com.zhy.controller.employer.applyforagree;

import net.sf.json.JSONArray;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * @author: zhangocean
 * @Date: 2018/4/17 13:50
 * Describe:
 */
@Controller
@RequestMapping("/apply")
public class ApplyForAgree {

    public JSONArray applyForOutsourcing(@AuthenticationPrincipal Principal principal){



        return null;
    }

}
