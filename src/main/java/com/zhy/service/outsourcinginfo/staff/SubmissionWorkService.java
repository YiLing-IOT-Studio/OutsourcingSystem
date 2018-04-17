package com.zhy.service.outsourcinginfo.staff;

import com.zhy.model.taskfollow.DynamicInformation;
import com.zhy.model.taskfollow.OrgZTree;
import com.zhy.service.mybatis.DynamicInformationService;
import com.zhy.service.mybatis.OrgZTreeService;
import com.zhy.utils.FileUtil;
import com.zhy.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;


/**
 * @author: zhangocean
 * @Date: 2018/4/13 18:20
 * Describe:
 */
@Service
public class SubmissionWorkService {

    @Autowired
    DynamicInformationService dynamicInformationService;
    @Autowired
    OrgZTreeService orgZTreeService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public int submissionWork(DynamicInformation dynamicInformation, List<MultipartFile> files) throws IOException {

        FileUtil fileUtil = new FileUtil();
        MultipartFile file = null;

        for(int i=0;i<files.size();i++){
            file = files.get(i);
            if(!file.isEmpty()){
                //获得上传文件地址
                String suffx = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String fileName = String.valueOf(System.currentTimeMillis());
                String filePath = this.getClass().getResource("/").getPath().substring(1) + "工作成果/" + dynamicInformation.getUploader() + "/" + dynamicInformation.getName() + "/";

                byte[] bytes = file.getBytes();
                //上传文件
                int uploadResult = fileUtil.uploadFile(bytes, filePath, fileName + suffx);
                dynamicInformation.setFilePath(filePath + fileName + suffx);
                //上传动态说明
                int saveDynamicInformation = dynamicInformationService.saveDynamicInformation(dynamicInformation);
                //将动态说明加入项目文件夹
                int pid = orgZTreeService.selectIdByOutsourcingName(dynamicInformation.getName());
                int id = orgZTreeService.selectIdByPidAndPhone(pid, dynamicInformation.getUploader());
                TimeUtil timeUtil = new TimeUtil();
                OrgZTree orgZTree = new OrgZTree(id, timeUtil.longToSixStringTime(Long.parseLong(fileName)), dynamicInformation.getUploader(), false);
                orgZTreeService.saveOrgTree(orgZTree);
                System.out.println("动态说明加入项目文件夹信息：" + orgZTree.toString());

                if(uploadResult != 1){
                    logger.info("员工" + dynamicInformation.getUploader() + "上传文件失败");
                    return 0;
                }
                if(saveDynamicInformation != 1){
                    logger.info("员工" + dynamicInformation.getUploader() + "上传工作成果失败");
                    return 0;
                }
            }
        }

        return 1;
    }

}
