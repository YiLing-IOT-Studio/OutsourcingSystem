/**
 * Created by 杨玉卿 on 2018/3/21.
 */
//取出cookie，设置请求头

var tBody=$("#tBody");
$("#li_xx_item").click(function(){
    $.ajax({
        type:"POST",
        url:"/getOutsourcingUserInfo",
        dataType:"json",
        data:{
        },
        success:function(data){
            var tBody=$("#tBody");
            tBody.html("");
            if(data.length==0){
                tBody.html("<tr><td class='alert alert-warning' colspan='7'>暂无接包人</td> </tr>");
            }
            $.each(data, function (index, obj) {
                if (index != (data.length)) {
                    var tr= $('<tr></tr>');
                    //姓名
                    var tName= $('<td></td>');
                    tName.append(obj['name']);
                    tr.append(tName);

                    //性别
                    var tGender= $('<td></td>');
                    if(obj['gender']=="gentleman") {
                        obj['gender']="男";
                    }
                    else{
                        obj['gender']="女";
                    }
                    tGender.append(obj['gender']);
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
                    if(obj['promise']=="true")
                    {
                        obj['promise']="✔";
                    }
                    else{
                        obj['promise']="✘";
                    }
                    tPromiss.append(obj['promise']);
                    tr.append(tPromiss);

                    //合同签订情况
                    var tContract= $('<td></td>');
                    if(obj['contract']=="true")
                    {
                        obj['contract']="✔";
                    }
                    else{
                        obj['contract']="✘";
                    }
                    tContract.append(obj['contract']);
                    tr.append(tContract);

                    tBody.append(tr);
                }
            });
        },
        error:function(){
            alert("人员信息查询请求失败!");
        }

    });
});

