package com.zhy.service.mybatis;

import com.zhy.model.outsourcing.OutsourcingInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 20:39 2018/3/3
 * Describe: 搜索外包信息的数据库操作
 */
public interface OutsourcingInfoService {

    /**
     * 查询所有外包信息
     * @return 查询到的所有外包信息
     */
    List<OutsourcingInfo> findAll();

    /**
     *  search 栏搜索外包信息
     * @param searchText 搜索栏输入的搜索信息
     * @return 查询到的所有外包信息
     */
    List<OutsourcingInfo> findBySearch(String searchText);

}
