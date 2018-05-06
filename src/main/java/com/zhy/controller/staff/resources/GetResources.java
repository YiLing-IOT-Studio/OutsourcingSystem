package com.zhy.controller.staff.resources;

import com.zhy.service.facecheck.DetectionService;
import com.zhy.service.mybatis.ResourceInfoService;
import com.zhy.service.mybatis.TaskInfoService;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: 2018/4/19 19:58
 * Describe:
 */
@Controller
public class GetResources {

    private final String picture = "picture";
    private final String video = "video";
    private final String document = "document";

    @Autowired
    TaskInfoService taskInfoService;
    @Autowired
    ResourceInfoService resourceInfoService;
    @Autowired
    DetectionService detectionService;

    @PostMapping("/sendFaceToLookRes")
    public @ResponseBody int sendFaceToLookRes(@Param("img") String img,
                                               @AuthenticationPrincipal Principal principal){

        System.out.println("人脸检测中.....");

        return detectionService.detectionService(img, principal.getName());
    }

    @PostMapping("/getTaskRank")
    public @ResponseBody int getTaskRank(@RequestParam("task_name") String taskName,
                                         @RequestParam("projectName") String projectName
    ){

        int taskRank = taskInfoService.getAuthorityByTaskNameAndProjectName(taskName, projectName);

        System.out.println("任务安全等级为：" + taskRank);
        return taskRank;

    }

    @PostMapping("/getResource")
    public @ResponseBody JSONArray getResource(@RequestParam("category") String category,
                                               @RequestParam("projectName") String projectName){


        List<String> getResourcePath = new ArrayList<>();
        if(category.equals(picture)){
            getResourcePath = resourceInfoService.getResourcePath(projectName, "图片");
        }
        else if(category.equals(video)){
            getResourcePath = resourceInfoService.getResourcePath(projectName, "视频");
        }
        else if (category.equals(document)){
            getResourcePath = resourceInfoService.getResourcePath(projectName, "文档");
        }

        JSONArray jsonArray = JSONArray.fromObject(getResourcePath);
        System.out.println("返回的文件路径" + jsonArray);
        return jsonArray;

    }


}
