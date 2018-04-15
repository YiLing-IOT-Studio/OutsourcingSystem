package com.zhy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: zhangocean
 * @Date: Created in 16:26 2018/4/8
 * Describe: 文件上传
 */
public class FileUtil {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public int uploadFile(byte[] file, String filePath, String fileName){
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.info("上传文件路径不存在");
        }
        try {
            out.write(file);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return 1;
    }

}
