package com.zhy.service.redis;

import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.repository.redis.OutsourcingRedisRepository;
import com.zhy.service.mybatis.UserRegisterService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 16:44 2018/3/12
 * Describe: redis访问操作
 */
@Service
public class OutsourcingRedisService {

    /**
     * redis中每一页外包信息集合和页码的key
     */
    private final String selectPageList = "outsourcingPageList";
    private final String pageMap = "pageMap";

    /**
     * redis中所有查询条件的数据
     */
    private final String selectAllList = "outsourcingAllList";


    @Autowired
    OutsourcingRedisRepository outsourcingRedisRepository;
    @Autowired
    UserRegisterService userRegisterService;

    /**
     * 将每一页查询到的外包信息集合储存到redis中
     * @param outsourcingPageList 查询到的外包信息集合
     * @param map 页码
     */
    public void saveByListAndMap(List<OutsourcingInfo> outsourcingPageList, Map<String, Integer> map){

        outsourcingRedisRepository.saveOutsourcingList(selectPageList, outsourcingPageList);
        outsourcingRedisRepository.saveOutsourcingPageNumber(pageMap, map);

    }

    /**
     * 从redis中获取每一页的外包信息
     * @return 将每一页的信息和页码转换成 JsonArray
     */
    public JSONArray getPageJsonArray(){

        List<OutsourcingInfo> list = outsourcingRedisRepository.getOutsourcingList(selectPageList);
        Map<String, Integer> pageMapInfo = outsourcingRedisRepository.getOutsourcingPage(pageMap);

        for(OutsourcingInfo o : list){
            o.setPublisher(userRegisterService.getUserNameByPhone(o.getPublisher()));
        }

        JSONArray jsonArray = JSONArray.fromObject(list);
        jsonArray.add(pageMapInfo);

        return jsonArray;
    }

    /**
     * 将所有查询条件的数据储存到redis中
     * @param outsourcingAllList 查询到的所有条件的数据
     */
    public void saveAllOutsourcingList(List<OutsourcingInfo> outsourcingAllList){

        outsourcingRedisRepository.saveOutsourcingList(selectAllList, outsourcingAllList);

    }

    /**
     * 从redis中获得所有查询条件的数据
     * @return 所有查询条件的List型数据
     */
    public List<OutsourcingInfo> getAllOutsourcingList(){
        return outsourcingRedisRepository.getOutsourcingList(selectAllList);
    }

    /**
     * 从redis中读取页码
     * @return 页码的map
     */
    public Map<String, Integer> getPageNumber(){
        return outsourcingRedisRepository.getOutsourcingPage(pageMap);
    }

}
