package com.zhy.service.mybatis;

import com.zhy.model.workachievement.OrgZTree;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 15:34 2018/4/1
 * Describe: 查询 ZTree 树
 */
@Service
public interface OrgZTreeService {

    /**
     * 查询树的所有子节点
     * @return 所有的子节点
     */
    List<OrgZTree> getRootNode();

    /**
     * 查询父节点下的所有子节点
     * @param pid 父节点
     * @return 父节点下的所有子节点
     */
    List<OrgZTree> getChildNode(int pid);

}
