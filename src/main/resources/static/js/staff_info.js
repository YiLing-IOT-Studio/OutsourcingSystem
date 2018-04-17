/**
 * Created by 杨玉卿 on 2018/4/10.
 */

var tBody=$("#tBody");
    $.ajax({
        type:"get",
        url:'/staff/getUserInfo',
        dataType:"json",
        async:false,
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
                    var oEdit=$("<td></td>");
                    var oBtn=$("<button class='btn btn-primary'>编辑</button>");
                    oEdit.append(oBtn);
                    tr.append(oEdit);
                    tBody.append(tr);

                    oBtn.click(function(){
                        var saveBtn=$('<button class="btn btn-success">保存</button>');
                        oEdit.html(saveBtn);
                        tName.html("<span class='glyphicon glyphicon-pen'></span><input type='text' name='staff_name'/>");
                        tGender.html('<span class="glyphicon glyphicon-pen"></span><select name="gender"><option>男</option><option>女</option></select>');
                        tPhone.html('<span class="glyphicon glyphicon-pen"></span><input type="text" name="telephone"/>');
                        saveBtn.click(function(){
                            if($("input[name='staffname']").val()==""){
                                alert("信息不完整");
                            }
                            else if($("input[name='gender']").val()==""){
                                alert("信息不完整");
                            }
                            else if($("input[name='telephone']").val()==""){
                                alert("信息不完整");
                            }
                            else{
                                $.ajax({
                                    type:"post",
                                    url:'/staff/getUserInfo',
                                    dataType:"json",
                                    async:false,
                                    data:{
                                        'staff_name':$("input[name='staffname']").val(),
                                        'staff_gender':$("input[name='gender']").val(),
                                        'staff_telephone':$("input[name='telephone']").val()
                                    },
                                    success:function(){
                                        alert("保存成功!");
                                    },
                                    error:function(){
                                        alert("保存失败！");
                                    }
                                })
                            }
                        })
                    })
                }
            });
        },
        error:function(){
            alert("个人资料查询请求失败!");
        }

    })