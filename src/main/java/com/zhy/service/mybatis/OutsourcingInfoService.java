package com.zhy.service.mybatis;

/**
 * @author: zhangocean
 * @Date: Created in 16:03 2018/3/9
 * Describe:
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
    int findAll();
}
