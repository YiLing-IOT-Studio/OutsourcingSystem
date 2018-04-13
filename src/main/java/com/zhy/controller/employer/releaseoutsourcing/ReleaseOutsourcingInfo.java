package com.zhy.controller.employer.releaseoutsourcing;

import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.outsourcinginfo.ReleaseOutsourcingService;
import com.zhy.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

/**
 * @author: zhangocean
 * @Date: Created in 15:44 2018/4/7
 * Describe: 发布外包
 */
@Controller
public class ReleaseOutsourcingInfo {

    @Autowired
    ReleaseOutsourcingService releaseOutsourcingService;

    @PostMapping("/releaseOutsourcing")
    public String releaseOutsourcing(@RequestParam("inputFile") MultipartFile file, HttpServletRequest request) throws ParseException, IOException {

        TimeUtil timeUtil = new TimeUtil();

        String name = request.getParameter("myName");
        int rank = Integer.parseInt(request.getParameter("rank"));
        String content = request.getParameter("content");
        String category = request.getParameter("category");
        int amount = Integer.parseInt(request.getParameter("money"));
        String requirement = request.getParameter("requirement");
        String publisher = request.getParameter("publisher");
        int progress = 0;
        String state = "报名中";

        long registrationDeadline = timeUtil.stringToLongTime(request.getParameter("enterTime"));
        long projectDeadline = timeUtil.stringToLongTime(request.getParameter("finishedTime"));
        long publishTimeForLong = Long.parseLong(request.getParameter("publishTime"))/1000;
        String publishTimeForString = timeUtil.longToStringTime(publishTimeForLong);
        System.out.println("项目发布时间：" + publishTimeForString);

        //上传文件的保存路径
        String fileName = file.getOriginalFilename();
        String filePath = this.getClass().getResource("/").getPath().substring(1) + "项目计划实施书" + name + "/";
        System.out.println("上传文件保存路径：" + filePath + fileName);

        OutsourcingInfo outsourcingInfo = new OutsourcingInfo(state, name, rank, category, content, publisher, publishTimeForString, requirement, registrationDeadline, projectDeadline, amount, filePath+fileName, progress);

        System.out.println("name is " + name+ " rank is " + rank+ " content is " + content+ " registrationTime is " + registrationDeadline+ " projectDeadline is " + projectDeadline+ " category is " + category+ " amount is " + amount+ " requirement is " + requirement + " publishTime is " + publishTimeForString + " publisher is " + publisher);

        int releaseResult = releaseOutsourcingService.releaseOutsourcing(outsourcingInfo, file, filePath, fileName);

        if(releaseResult == 1){
            return "redirect:release?Success";
        }
        return "redirect:release?Fail";

    }

}
