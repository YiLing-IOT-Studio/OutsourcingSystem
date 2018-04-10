package com.zhy.service.mybatis;

import com.zhy.model.outsourcing.OutsourcingInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 16:03 2018/3/9
 * Describe: 搜索外包信息的数据库操作
 */
public interface OutsourcingInfoService {

    /**
     *  计算搜索框查询的数据的数量
     * @param searchText 搜索框输入信息
     * @return 查询数据库中与输入框输入信息相似的数据的条数
     */
    int countSearchText(String searchText);

    /**
     * 查找所有外包信息
     * @return 查找的条数
     */
    int countAll();

    /**
     * 通过搜索框搜索查询满足条件的所有外包信息
     * @param searchText 搜索框的输入
     * @return 满足搜索框输入条件的所有外包信息
     */
    List<OutsourcingInfo> findBySearch(@Param("searchText") String searchText);

    /**
     * 查询所有的外包信息
     * @return 所有的外包信息
     */
    List<OutsourcingInfo> findAllOutsourcing();

    /**
     * 进度统计
     * @return 项目名和进度
     */
    List<Map<String, Object>> findOutsourcingAndProgress();

    /**
     * 发布外包
     * @param outsourcingInfo 外包信息
     * @return 保存是否成功
     */
    int saveOutsourcingInfo(OutsourcingInfo outsourcingInfo);
}
