package com.zhy.model.workachievement;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: Created in 14:59 2018/4/3
 * Describe:
 */
@Data
public class DynamicInformation {

    private int id;
    private String name;
    private String principal;
    private long uploadTime;
    private String uploadInstructions;

}
