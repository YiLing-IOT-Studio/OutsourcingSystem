/**
 * Created by 杨玉卿 on 2018/4/10.
 */
var tBody=$("#tBody");
    $.ajax({
        type:"get",
        url:'/staff/getUserInfo',
        dataType:"json",
        data:{

        },
        success:function(data){
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
            alert("请求失败!");
        }

    })