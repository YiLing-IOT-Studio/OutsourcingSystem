package com.zhy.service.outsourcinginfo;

import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.redis.OutsourcingRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/11 22:12
 * Describe: 通过id从缓存中读取该id信息
 */
@Service
public class GetOneOutsourcingServer{

    @Autowired
    OutsourcingRedisService outsourcingRedisService;

    public OutsourcingInfo getOneOutsourcingById(int id){
        List<OutsourcingInfo> list = outsourcingRedisService.getAllOutsourcingList();

        for(OutsourcingInfo o : list){
            if(o.getId() == id){
                return o;
            }
        }

        return null;

    }

}
