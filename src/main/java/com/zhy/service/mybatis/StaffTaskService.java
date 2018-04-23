package com.zhy.service.mybatis;

import com.zhy.model.task.StaffTask;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/16 11:39
 * Describe:
 */
@Service
public interface StaffTaskService {

    /**
     * 通过任务id和手机号查找该接包人的该任务的状态
     * @param id 任务id
     * @param phone 手机号
     * @return 申请状态
     */
    String selectStateByIdAndPhone(int id, String phone);

    /**
     * 保存任务id\接任务状态和手机号
     * @param phone 手机号
     * @param id 任务id
     */
    void saveStaffPhoneAndTaskId(String phone, int id, String state);

    /**
     * 通过手机号和状态获取任务id
     * @param phone 手机号
     * @param state 状态
     * @return 任务id
     */
    List<StaffTask> selectTidByPhoneAndState(String phone);

    /**
     * 通过任务id和状态获得所有申请该任务的手机号
     * @param taskId 任务id
     * @param state 任务状态
     * @return
     */
    List<String> getPhoneByTaskIdAndState(int taskId, String state);
}
