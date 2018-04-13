package com.zhy.service.outsourcinginfo.staff;

import com.zhy.model.taskfollow.DynamicInformation;
import com.zhy.service.mybatis.DynamicInformationService;
import com.zhy.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @author: zhangocean
 * @Date: 2018/4/13 18:20
 * Describe:
 */
@Service
public class SubmissionWorkService {

    @Autowired
    DynamicInformationService dynamicInformationService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public int submissionWork(DynamicInformation dynamicInformation, MultipartFile file, String filePath, String fileName) throws IOException {

        FileUtil fileUtil = new FileUtil();
        int uploadFileResult = fileUtil.uploadFile(file.getBytes(), filePath, fileName);
        if(uploadFileResult == 1){
            logger.info("文件上传成功");
        } else {
            logger.info("文件上传失败");
            return 0;
        }


        int saveDynamicInformationResult = dynamicInformationService.saveDynamicInformation(dynamicInformation);

        return saveDynamicInformationResult;
    }

}
