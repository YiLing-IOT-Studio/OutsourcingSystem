package com.zhy.constant;

/**
 * @author: zhangocean
 * @Date: 2018/4/15 14:18
 * Describe: 任务状态
 */
public interface TaskState {

    /**
     * 任务待领取
     */
    String TASK_AWAIT = "待领取";

    /**
     * 任务申请中
     */
    String TASK_APPLY = "申请中";

    /**
     * 任务进行中
     */
    String TASK_CONDUCT = "进行中";

    /**
     * 任务已完成
     */
    String TASK_FINISH = "已完成";

}
