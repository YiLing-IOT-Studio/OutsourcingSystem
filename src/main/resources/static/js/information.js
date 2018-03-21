/**
 * Created by 杨玉卿 on 2018/3/21.
 */
//请求人员信息
var tBody=$("#tBody");
    $.ajax({
        type:"POST",
        url:"/",
        dataType:"json",
        data:{
            "staff_information":"staff_information"
        },
        success:function(data){
            $.each(data, function (index, obj) {
                if (index != (data.length - 1)) {
                    var tr= $('<tr></tr>');
                    //姓名
                    var tName= $('<td></td>');
                    tName.append(obj['name']);
                    tr.append(tName);

                    //性别
                    var tGender= $('<td></td>');
                    tGender.append(obj['Gender']);
                    tr.append(tGender);

                    //电话
                    var tPhone= $('<td></td>');
                    tPhone.append(obj['phone']);
                    tr.append(tPhone);

                    //负责项目
                    var tProject= $('<td></td>');
                    tProject.append(obj['project']);
                    tr.append(tProject);

                    //分工细则
                    var tTask= $('<td></td>');
                    tTask.append(obj['task']);
                    tr.append(tTask);

                    //是否签订保密协议
                    var tPromiss= $('<td></td>');
                    tPromiss.append(obj['promiss']);
                    tr.append(tPromiss);

                    //合同签订情况
                    var tContract= $('<td></td>');
                    tContract.append(obj['contract']);
                    tr.append(tContract);

                    tBody.append(tr);
                }
            });
        },
        error:function(){
           alert("请求失败!");
        }

    })