package com.zhy.controller.employer.applyforagree;

import com.zhy.constant.ApplyState;
import com.zhy.constant.TaskState;
import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.model.outsourcing.OutsourcingUserInfo;
import com.zhy.model.task.TaskInfo;
import com.zhy.service.mybatis.*;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: 2018/4/17 13:50
 * Describe:
 */
@Controller
public class ApplyForAgree {

    @Autowired
    OutsourcingInfoService outsourcingInfoService;
    @Autowired
    ApplyForOutsourcingService applyForOutsourcingService;
    @Autowired
    OutsourcingUserInfoService outsourcingUserInfoService;
    @Autowired
    UserRegisterService userRegisterService;
    @Autowired
    TaskInfoService taskInfoService;
    @Autowired
    StaffTaskService staffTaskService;

    @PostMapping("/applyForOutsourcing")
    @ResponseBody
    public  JSONArray applyForOutsourcing(@AuthenticationPrincipal Principal principal){

        List<OutsourcingInfo> outsourcingInfoList = outsourcingInfoService.getAllNameAndRankByPhoneAndState(principal.getName(), "报名中");

        List<Map<String, Object>> list = new ArrayList<>();
        List<String> phones;
        OutsourcingUserInfo outsourcingUserInfo;
        Map<String, Object> map;

        for(OutsourcingInfo o : outsourcingInfoList){
            phones = applyForOutsourcingService.getPhoneByNameAndState(o.getName(), ApplyState.APPLYSTATE_APPLY);
            if(!phones.isEmpty()){
                map = new HashMap<>();
                map.put("projectName", o.getName());
                map.put("rank", o.getRank());
                for(String phone : phones){
                    map.put("proposer", userRegisterService.getUserNameByPhone(phone));
                    outsourcingUserInfo = outsourcingUserInfoService.getUserInfoByPhoneAndProjectName(phone, o.getName());
                    map.put("gender", outsourcingUserInfo.getGender());
                    map.put("phone", outsourcingUserInfo.getPhone());
                    map.put("promise", outsourcingUserInfo.getPromise());
                    map.put("contract",outsourcingUserInfo.getContract());
                    list.add(map);
                }
            }
        }

        JSONArray jsonArray = JSONArray.fromObject(list);

        System.out.println("外包申请情况：" + jsonArray);
        return jsonArray;
    }

    @PostMapping("/applyForTask")
    @ResponseBody
    public JSONArray applyForTask(@AuthenticationPrincipal Principal principal){

        List<TaskInfo> taskInfoList = taskInfoService.getTaskInfoByPhone(principal.getName());
        OutsourcingUserInfo outsourcingUserInfo;
        Map<String, Object> map;
        List<Map<String, Object>> list = new ArrayList<>();

        for(TaskInfo taskInfo : taskInfoList){

            List<String> phones = staffTaskService.getPhoneByTaskIdAndState(taskInfo.getId(), TaskState.TASK_APPLY);
            if(!phones.isEmpty()){
                map = new HashMap<>();
                map.put("projectName", taskInfo.getProjectName());
                map.put("taskName", taskInfo.getTaskName());
                map.put("rank", taskInfo.getAuthority());
                for(String phone : phones){
                    outsourcingUserInfo = outsourcingUserInfoService.getUserInfoByPhoneAndProjectName(phone, taskInfo.getProjectName());
                    map.put("gender", outsourcingUserInfo.getGender());
                    map.put("proposer", userRegisterService.getUserNameByPhone(outsourcingUserInfo.getPhone()));
                    map.put("phone",outsourcingUserInfo.getPhone());
                    map.put("promise", outsourcingUserInfo.getPromise());
                    map.put("contract",outsourcingUserInfo.getContract());
                    list.add(map);
                }
            }
        }
        JSONArray jsonArray = JSONArray.fromObject(list);

        System.out.println("外包任务申请情况：" + jsonArray);
        return jsonArray;
    }

    @RequestMapping("/agreeForOutsourcing")
    @ResponseBody
    public String agreeForOutsourcing(@Param("tag") String tag,
                                      @RequestParam("projectName") String projectName,
                                      @RequestParam("proposer") String proposer){

        int applyResult = applyForOutsourcingService.applyForLoan(projectName, userRegisterService.getPhoneByUserName(proposer), tag);
        System.out.println("审批结果：" + applyResult);

        return "1";
    }

    @RequestMapping("/agreeForTask")
    @ResponseBody
    public String agreeForTask(@Param("tag") String tag){


        System.out.println("tag is " + tag);
//        int applyResult = applyForOutsourcingService.applyForLoan(projectName, userRegisterService.getPhoneByUserName(proposer), "1");
//        System.out.println("审批结果：" + applyResult);

        return "1";
    }



}
