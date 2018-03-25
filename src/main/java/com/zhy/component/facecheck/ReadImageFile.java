package com.zhy.component.facecheck;

import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * @author: zhangocean
 * @Date: Created in 20:04 2018/3/24
 * Describe:
 */
@Component
public class ReadImageFile {

    public byte[] readImageFile(String img){

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] result = null;
        try {
            result = decoder.decodeBuffer(img);
            for(int i=0;i<result.length;i++){
                if(result[i]<0){
                    result[i] += 256;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return result;

    }

}
