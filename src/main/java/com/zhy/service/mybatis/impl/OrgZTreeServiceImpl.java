package com.zhy.service.mybatis.impl;

import com.zhy.mapper.OrgZTreeMapper;
import com.zhy.model.taskfollow.OrgZTree;
import com.zhy.service.mybatis.OrgZTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 15:36 2018/4/1
 * Describe:
 */
@Service
public class OrgZTreeServiceImpl implements OrgZTreeService {

    @Autowired
    OrgZTreeMapper orgZTreeMapper;

    @Override
    public List<OrgZTree> getRootNode(String phone) {
        return orgZTreeMapper.getRootNode(phone);
    }

    @Override
    public List<OrgZTree> getChildNode(int pid) {
        return orgZTreeMapper.getChildNode(pid);
    }

    @Override
    public List<String> getAllOutsourcingName() {
        return orgZTreeMapper.getAllOutsourcingName();
    }

    @Override
    public void saveOrgTree(OrgZTree orgZTree) {
        orgZTreeMapper.saveOrgTree(orgZTree);
    }
}
