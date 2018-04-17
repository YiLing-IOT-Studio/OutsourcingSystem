package com.zhy.controller.staff.submissionwork;

import com.zhy.model.taskfollow.DynamicInformation;
import com.zhy.service.outsourcinginfo.staff.SubmissionWorkService;
import com.zhy.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/13 16:52
 * Describe: 提交工作成果
 */
@Controller
public class SubmissionWork {

    @Autowired
    SubmissionWorkService submissionWorkService;

    @PostMapping("/submissionWork")
    @ResponseBody
    public int submissionWork(@RequestParam("outsourcingName") String outsourcingName,
                               @RequestParam("fileDescription") String fileDescription,
                               @RequestParam("progress") String progress,
                               @AuthenticationPrincipal Principal principal,
                              HttpServletRequest request) throws IOException {

        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("upl");

        //获得当前时间并转换成字符串型
        TimeUtil timeUtil = new TimeUtil();
        Date now = new Date();
        String stringTime = timeUtil.longToSixStringTime(now.getTime());

        DynamicInformation dynamicInformation = new DynamicInformation(outsourcingName, principal.getName(), stringTime,
                                                Integer.valueOf(progress), fileDescription);

        int submissionResult = submissionWorkService.submissionWork(dynamicInformation, files);

        if(submissionResult == 1){
            return 1;
        }
        return 0;
    }

}
