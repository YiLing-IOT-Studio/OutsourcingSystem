package com.zhy.service.outsourcinginfo;

import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.mybatis.OutsourcingInfoService;
import com.zhy.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: zhangocean
 * @Date: Created in 16:36 2018/4/8
 * Describe: 发布外包
 */
@Service
public class ReleaseOutsourcingService {

    @Autowired
    OutsourcingInfoService outsourcingInfoService;

    public int releaseOutsourcing(OutsourcingInfo outsourcingInfo, MultipartFile file, String filePath, String fileName) throws IOException {

        FileUtil fileUtil = new FileUtil();
        int uploadResult = fileUtil.uploadFile(file.getBytes(), filePath, fileName);
        if(uploadResult == 1){
            System.out.println("文件上传成功");
        } else {
            System.out.println("文件上传失败");
            return 0;
        }

        int releaseOutsourcingResult = outsourcingInfoService.saveOutsourcingInfo(outsourcingInfo);

        return releaseOutsourcingResult;
    }

}
