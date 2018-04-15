package com.zhy.controller.employer.releaseresource;

import com.zhy.service.mybatis.TaskInfoService;
import com.zhy.service.outsourcinginfo.employer.ReleaseResourceService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/13 15:39
 * Describe: 发布外包资源
 */
@Controller
@RequestMapping("/resource")
public class ReleaseResource {

    @Autowired
    TaskInfoService taskInfoService;
    @Autowired
    ReleaseResourceService releaseResourceService;

    @PostMapping("/releaseResource")
    @ResponseBody
    public int releaseResource(@RequestParam("projectName") String projectName,
                                @RequestParam("myTaskName") String taskName,
                                @AuthenticationPrincipal Principal principal,
                                HttpServletRequest request){

        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("upl");

        return releaseResourceService.releaseResource(projectName, taskName, files, principal.getName());
    }

    @GetMapping("/getTaskNameByProjectName")
    @ResponseBody
    public JSONArray getTaskNameByProjectName(@RequestParam("projectName") String projectName){

        List<String> taskList = taskInfoService.getTaskNameByProjectName(projectName);
        System.out.println(projectName + "下的任务有：" + taskList.toString());
        JSONArray jsonArray = JSONArray.fromObject(taskList);

        return jsonArray;
    }

}
