package com.zhy.service.outsourcinginfo.employer;

import com.zhy.constant.FileSuffx;
import com.zhy.model.resource.ResourceInfo;
import com.zhy.service.mybatis.ResourceInfoService;
import com.zhy.utils.FileUtil;
import com.zhy.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/14 21:35
 * Describe:
 */
@Service
public class ReleaseResourceService {

    @Autowired
    ResourceInfoService resourceInfoService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public int releaseResource(String projectName, String taskName, List<MultipartFile> files, String promulgator){

        MultipartFile file = null;
        String fileType;
        int fileCount=0;

        //发布时间
        TimeUtil timeUtil = new TimeUtil();
        Date now = new Date();
        String nowTimeForString = timeUtil.longToSixStringTime(now.getTime());

        ResourceInfo resourceInfo = new ResourceInfo(projectName, promulgator, taskName, nowTimeForString);

        for (int i=0;i<files.size();i++) {
            file = files.get(i);
            if (!file.isEmpty()) {
                String suffx = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                for (String s : FileSuffx.PICTURE_SUFFX) {
                    if (suffx.toUpperCase().equals(s)) {
                        fileType = "图片";
                        releaseResourceForSuffx(fileType, file, resourceInfo);
                        fileCount++;
                    }
                }
                for (String s : FileSuffx.VIDEO_SUFFX) {
                    if (suffx.toUpperCase().equals(s)) {
                        fileType = "视频";
                        releaseResourceForSuffx(fileType, file, resourceInfo);
                        fileCount++;
                    }
                }
                for (String s : FileSuffx.DOCUMENT_SUFFX) {
                    if (suffx.toUpperCase().equals(s)) {
                        fileType = "文档";
                        releaseResourceForSuffx(fileType, file, resourceInfo);
                        fileCount++;
                    }
                }
            }
        }
        if(fileCount == files.size()){
            System.out.println("所有的文件都已上传成功");
            return 1;
        }
        System.out.println("有文件上传失败");
        return 0;
    }

    private void releaseResourceForSuffx(String fileType, MultipartFile file, ResourceInfo resourceInfo){

        FileUtil fileUtil = new FileUtil();
        int fileUploadResult = 0;

        //获得上传地址
        String fileName = file.getOriginalFilename();
        String filePath = this.getClass().getResource("/").getPath().substring(1) + "项目资料/" + resourceInfo.getProjectName() + "/" + fileType + "/";
        System.out.println("文件上传路径为：" + filePath + fileName);
        try {
            fileUploadResult = fileUtil.uploadFile(file.getBytes(), filePath, fileName);
            resourceInfo.setResourcePath(filePath + fileName);
            if(fileUploadResult == 1){
                resourceInfoService.releaseResourceInfo(resourceInfo);
            } else {
                logger.info("项目" + resourceInfo.getProjectName() + "的" + resourceInfo.getTaskName() + "任务" + "的图片文件上传失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
