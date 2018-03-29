package com.zhy.component.outsourcing.dealwithtask;

import com.zhy.model.outsourcing.ExecutionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 15:40 2018/3/29
 * Describe: 分配任务
 */
@Component
public class AssigningTask {

    @Autowired
    CutOutTaskDev cutOutTaskDev;

    @Autowired
    CutOutStaffName cutOutStaffName;

    public List<ExecutionInfo> assigningTask(String outsourcingName, String taskContent, String taskDev, String staffName){


        List<String> taskDevList = cutOutTaskDev.cutOutTaskDev(taskDev);
        List<String> staffNameList = cutOutStaffName.cutOutStaffName(staffName);

        Iterator taskDevIt = taskDevList.iterator();
        Iterator staffNameIt = staffNameList.iterator();

        List<ExecutionInfo> executionInfo = new ArrayList<>();
        while (taskDevIt.hasNext() && staffNameIt.hasNext()){

            ExecutionInfo addON = new ExecutionInfo();
            addON.setTask((String) taskDevIt.next());
            addON.setUsername((String) staffNameIt.next());
            addON.setOutsourcingName(outsourcingName);
            addON.setTaskContent(taskContent);

            executionInfo.add(addON);

        }

        return executionInfo;

    }

}
