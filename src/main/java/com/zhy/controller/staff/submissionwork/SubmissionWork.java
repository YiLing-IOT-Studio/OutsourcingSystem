package com.zhy.controller.staff.submissionwork;

import com.zhy.model.taskfollow.DynamicInformation;
import com.zhy.service.outsourcinginfo.staff.SubmissionWorkService;
import com.zhy.utils.FileUtil;
import com.zhy.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Date;

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
    public String submissionWork(@RequestParam String outsourcingName,
                               @RequestParam String fileDescription,
                               @RequestParam String progress,
                               @RequestParam MultipartFile upl,
                               @AuthenticationPrincipal Principal principal,
                               HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        //获得当前时间并转换成字符串型
        TimeUtil timeUtil = new TimeUtil();
        Date now = new Date();
        String stringTime = timeUtil.longToStringTime(now.getTime());

        //保存文件存储地址
        String suffx = upl.getOriginalFilename().substring(upl.getOriginalFilename().lastIndexOf("."));
        String fileName = String.valueOf(now.getTime()) + suffx;
        String filePath = this.getClass().getResource("/").getPath().substring(1) + "工作成果/" + principal.getName() + "/" + outsourcingName + "/";
        System.out.println("文件保存路径为：" + filePath + fileName);

        DynamicInformation dynamicInformation = new DynamicInformation(outsourcingName, principal.getName(), stringTime,
                                                Integer.valueOf(progress), fileDescription, filePath+fileName );

        int submissionResult = submissionWorkService.submissionWork(dynamicInformation, upl, filePath, fileName);

        return "submission";
    }

}
