/**
 * Created by 杨玉卿 on 2018/4/15.
 */
$.ajax({
    type:"get",
    url:"/",
    dataType:"json",
    async:false,
    data:{},
    success:function(data){
        //清空
        var oDiv=$("#firstBody");
        oDiv.html("");
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
                oTd2.append(obj['tank']);
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
                var oAgree=$('<button class="btn btn-primary">同意</button>');
                var oDisagree=$('<button class="btn btn-danger">拒绝</button>');
                oTd5.append(oAgree+'&nbsp;&nbsp;&nbsp;&nbsp;'+oDisagree);
                oTr.append(oTd5);
                var oInfo=$("<div class='modal'></div>");
                var oName=$("<p></p>");
                oName.append("姓名:"+obj['proposer']);
                oInfo.append(oName);
                var oGender=$("<p></p>")
                if(obj['gender']=="gentleman") {
                    obj['gender']="男";
                }
                else{
                    obj['gender']="女";
                }
                oGender.append("性别:"+obj['gender']);
                oInfo.append(oGender);
                var oPhone=$("<p></p>");
                oPhone.append("电话:"+obj['phone']);
                oInfo.append(oPhone);
                var oAgreement=$("<p></p>");
                if(obj['promise']=="true")
                {
                    obj['promise']="已签订";
                }
                else{
                    obj['promise']="未签订";
                }
                oAgreement.append("是否签订保密协议:"+obj['promise']);
                oInfo.append(oAgreement);
                var oContract=$("<p></p>");
                if(obj['contract']=="true")
                {
                    obj['contract']="已签订";
                }
                else{
                    obj['contract']="未签订";
                }
                oContract.append("是否签订合同:"+obj['contract']);
                oInfo.append(oContract);

                var oClose=$("<div class='text-right'><button class='btn btn-primary'>确定</button></div>");
                oInfo.append(oClose);
                oDiv.append(oInfo);

                oCheck.click(function(){
                    showFun(oInfo);
                    oClose.click(function(){
                        hideFun(oInfo);
                    });
                });
                oAgree.click(function(){
                    $.ajax({
                        type:"post",
                        url:"/？",
                        dataType:"json",
                        async:false,
                        data:{
                            'projectName':projectName,
                            'proposer':proposer,
                            'result':1
                        },
                        success:function(){
                            oTd5.html('<button class="btn btn-success">已同意</button>');
                        },
                        error:function(){
                            alert("请求失败，请稍后重试！");
                        }
                    })
                });
                oDisagree.click(function(){
                    $.ajax({
                        type:"post",
                        url:"/",
                        dataType:"json",
                        async:false,
                        data:{
                            'projectName':projectName,
                            'proposer':proposer,
                            'result':0
                        },
                        success:function(){
                            oTd5.html('<button class="btn btn-success">已拒绝</button>');
                        },
                        error:function(){
                            alert("请求失败，请稍后重试！");
                        }
                    })
                })

            }

        });
    }

});
//测试用
// var oDiv=$("#firstBody");
// alert("!");
// var oCheck=$('<button class="btn btn-info">详细信息</button>');
// oDiv.append(oCheck);
// oCheck.click(function(){
//     alert("!");
// })
//modal
function showFun(info) {
    var bg = document.getElementById("bg");
    info.style.display = "block";
    bg.style.display = "block";
}
function hideFun(info){
    var bg = document.getElementById("bg");
    info.style.display = "none";
    bg.style.display = "none";
}
