/**
 * Created by 杨玉卿 on 2018/4/15.
 */
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(header, token);
});

$.ajax({
    type:"post",
    url:"/applyForOutsourcing",
    dataType:"json",

    data:{},
    success:function(data){
        //清空
        var oDiv=$("#firstBody");
        oDiv.html("");
        if(data.length==0){
            oDiv.html("<tr><td class='alert alert-warning' colspan='5'>当前无申请信息！</td> </tr>");
        }
        //插入
        $.each(data,function(index,obj){
            if(index!=(data.length)){
                //放入一行
                var oTr=$("<tr></tr>");
                oDiv.append(oTr);
                //放入单元格
                var oTd1=$("<td></td>");
                var projectName=obj['projectName'];
                oTd1.append(projectName);
                oTr.append(oTd1);
                var oTd2=$("<td></td>");
                if(obj['rank']==1){
                    obj['rank']="★";
                }
                else if(obj['rank']==2){
                    obj['rank']="★★";
                }
                else{
                    obj['rank']="★★★";
                }
                oTd2.append(obj['rank']);
                oTr.append(oTd2);
                var oTd3=$("<td></td>");
                var proposer=obj['proposer'];
                oTd3.append(proposer);
                oTr.append(oTd3);
                var oTd4=$("<td></td>");
                var oCheck=$('<button class="btn btn-info">详细信息</button>');
                oTd4.append(oCheck);
                oTr.append(oTd4);
                var oTd5=$("<td></td>");
                var oAgree=$('<button class="btn btn-primary agreeBtn">同意</button>');
                var oDisagree=$('<button class="btn btn-danger disagreeBtn">拒绝</button>');
                oTd5.append(oAgree);
                oTd5.append("&nbsp;&nbsp;");
                oTd5.append(oDisagree);
                oTr.append(oTd5);
                var oInfo=$("<div class='modal'></div>");

                var oName=$("<p></p>");
                oName.append("<span class='glyphicon glyphicon-user red'></span>"+"姓名:"+obj['proposer']);
                oInfo.append(oName);
                var oGender=$("<p></p>");
                if(obj['gender']=="gentleman") {
                    obj['gender']="男";
                }
                else{
                    obj['gender']="女";
                }
                oGender.append("<span class='glyphicon glyphicon-asterisk red'></span>"+"性别:"+obj['gender']);
                oInfo.append(oGender);
                var oPhone=$("<p></p>");
                oPhone.append("<span class='glyphicon glyphicon-earphone red'></span>"+"电话:"+obj['phone']);
                oInfo.append(oPhone);
                var oAgreement=$("<p></p>");
                if(obj['promise']=="true")
                {
                    obj['promise']="已签订";
                }
                else{
                    obj['promise']="未签订";
                }
                oAgreement.append("<span class='glyphicon glyphicon-envelope red'></span>"+"是否签订保密协议:"+obj['promise']);
                oInfo.append(oAgreement);
                var oContract=$("<p></p>");
                if(obj['contract']=="true")
                {
                    obj['contract']="已签订";
                }
                else{
                    obj['contract']="未签订";
                }
                oContract.append("<span class='glyphicon glyphicon-envelope red'></span>"+"是否签订合同:"+obj['contract']);
                oInfo.append(oContract);

                var oClose=$("<div class='text-right'><button class='btn btn-success'>确定</button></div>");
                oInfo.append(oClose);
                oDiv.append(oInfo);

                oCheck.click(function(){
                    oInfo.css('display','block');
                    var bg = document.getElementById("bg");
                    bg.style.display = "block";
                    oClose.click(function(){
                        oInfo.css('display','none');
                        var bg = document.getElementById("bg");
                        bg.style.display = "none";
                    });
                });
                oAgree.add(oDisagree).click(function(event){
                    var tag=event.target.innerHTML;
                    if(tag=="同意"){tag=1}
                    else{tag=0}
                    var str="/agreeForOutsourcing?tag="+tag;

                    $.ajax({
                        type:"post",
                        url:str,

                        dataType:"json",
                        data:{
                            'projectName':projectName,
                            'proposer':proposer
                        },
                        success:function(data){
                            if(data==1)
                            {
                                oTd5.html('<button class="btn btn-success">已同意</button>');
                            }
                            else{
                                oTd5.html('<button class="btn btn-success">已拒绝</button>');
                            }

                        },
                        error:function(){
                            alert("请求失败，请稍后重试！");
                        }
                    })
                });

            }

        });
    }
});
$.ajax({
    type:"post",
    url:"/applyForTask",
    dataType:"json",

    data:{},
    success:function(data){
        //清空
        var oDiv=$("#secondBody");
        oDiv.html("");
        if(data.length==0){
            oDiv.html("<tr><td class='alert alert-warning' colspan='6'>当前无申请信息！</td> </tr>");
        }
        //插入
        $.each(data,function(index,obj){
            if(index!=(data.length)){
                //放入一行
                var oTr=$("<tr></tr>");
                oDiv.append(oTr);
                //放入单元格
                var oTd0=$("<td></td>");
                var projectName=obj['projectName'];
                oTd0.append(projectName);
                oTr.append(oTd0);
                var oTd1=$("<td></td>");
                var taskName=obj['taskName'];
                oTd1.append(taskName);
                oTr.append(oTd1);
                var oTd2=$("<td></td>");
                if(obj['rank']==1){
                    obj['rank']="★";
                }
                else if(obj['rank']==2){
                    obj['rank']="★★";
                }
                else{
                    obj['rank']="★★★";
                }
                oTd2.append(obj['rank']);
                oTr.append(oTd2);
                var oTd3=$("<td></td>");
                var proposer=obj['proposer'];
                oTd3.append(proposer);
                oTr.append(oTd3);
                var oTd4=$("<td></td>");
                var oCheck=$('<button class="btn btn-info">详细信息</button>');
                oTd4.append(oCheck);
                oTr.append(oTd4);
                var oTd5=$("<td></td>");
                var oAgree=$('<button class="btn btn-primary agreeBtn">同意</button>');
                var oDisagree=$('<button class="btn btn-danger disagreeBtn">拒绝</button>');
                oTd5.append(oAgree);
                oTd5.append("&nbsp;&nbsp;");
                oTd5.append(oDisagree);
                oTr.append(oTd5);
                var oInfo=$("<div class='modal'></div>");

                var oName=$("<p></p>");
                oName.append("<span class='glyphicon glyphicon-user red'></span>"+"姓名:"+obj['proposer']);
                oInfo.append(oName);
                var oGender=$("<p></p>")
                if(obj['gender']=="gentleman") {
                    obj['gender']="男";
                }
                else{
                    obj['gender']="女";
                }
                oGender.append("<span class='glyphicon glyphicon-asterisk red'></span>"+"性别:"+obj['gender']);
                oInfo.append(oGender);
                var oPhone=$("<p></p>");
                oPhone.append("<span class='glyphicon glyphicon-earphone red'></span>"+"电话:"+obj['phone']);
                oInfo.append(oPhone);
                var oAgreement=$("<p></p>");
                if(obj['promise']=="true")
                {
                    obj['promise']="已签订";
                }
                else{
                    obj['promise']="未签订";
                }
                oAgreement.append("<span class='glyphicon glyphicon-envelope red'></span>"+"是否签订保密协议:"+obj['promise']);
                oInfo.append(oAgreement);
                var oContract=$("<p></p>");
                if(obj['contract']=="true")
                {
                    obj['contract']="已签订";
                }
                else{
                    obj['contract']="未签订";
                }
                oContract.append("<span class='glyphicon glyphicon-envelope red'></span>"+"是否签订合同:"+obj['contract']);
                oInfo.append(oContract);

                var oClose=$("<div class='text-right'><button class='btn btn-success'>确定</button></div>");
                oInfo.append(oClose);
                oDiv.append(oInfo);

                oCheck.click(function(){
                    oInfo.css('display','block');
                    var bg = document.getElementById("bg");
                    bg.style.display = "block";
                    oClose.click(function(){
                        oInfo.css('display','none');
                        var bg = document.getElementById("bg");
                        bg.style.display = "none";
                    });
                });
                oAgree.add(oDisagree).click(function(event){
                    var tag=event.target.innerHTML;
                    if(tag=="同意"){tag=1}
                    else{tag=0}
                    var str="/agreeForTask?tag="+tag;
                    $.ajax({
                        type:"post",
                        url:str,
                        dataType:"json",

                        contentType: "application/json;charset=utf-8",
                        data:{
                            'projectName':projectName,
                            'taskName':taskName,
                            'proposer':proposer
                        },
                        success:function(data){
                            if(data==1)
                            {
                                oTd5.html('<button class="btn btn-success">已同意</button>');
                            }
                            else{
                                oTd5.html('<button class="btn btn-success">已拒绝</button>');
                            }

                        },
                        error:function(){
                            alert("请求失败，请稍后重试！");
                        }
                    })
                });


            }

        });
    }
});

