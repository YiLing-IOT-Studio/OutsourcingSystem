package com.zhy.constant;

/**
 * @author: zhangocean
 * @Date: 2018/4/15 22:06
 * Describe:接包状态
 */
public interface OutsourcingState {

    /**
     * 接包状态--未通过
     */
    String APPLYSTATE_NOTPASS = "未通过";

    /**
     * 接包状态--待审核
     */
    String APPLYSTATE_APPLY = "待审核";

    /**
     * 接包状态--已接包
     */
    String APPLYSTATE_ACCEPTED = "已接包";

    /**
     * 接包状态--已完成
     */
    String APPLYSTATE_FINISH = "已完成";


}
