/**
 * Created by 杨玉卿 on 2018/4/3.
 */
var oDiv=$("#follow");
//点击动态，显示所有文件夹
$("#follow-tab").click(function(){
    $.ajax({
        type:"POST",
        url:"/dynamicState/getAllOutsourcingName",
        dataType:"json",
        data:{

        },
        success:function(data){
            var clear="";
            oDiv.html(clear);
            var i;
            for(i in data){
                var oFolder=$('<div class="my-inline-block text-center">'+'</div>');
                oFolder.append('<span class="glyphicon glyphicon-folder-close my-folder"></span>'+
                    '<span class="folder-name">'+data[i]+'</span>');
                oDiv.append(oFolder);
            }
        },
        error:function(){
            alert("请求失败！");
        }
    })


});
//点击文件夹，进入每个负责人的上传信息
$(".my-inline-block").click(function(){
    var name=$(this).find(".folder-name").text();
    console.log(name);
    $.ajax({
        type:"POST",
        url:"/dynamicState/gerDynamicInformation",
        dataType:"json",
        data:{
            "name":name
        },
        success:function(data){
            var i;
            for(i in data){
                var oDiv=$("#follow");
                var clear='';
                oDiv.html(clear);
                //name
                var oH2=$("<h2 class='leader my-h2'></h2>");
                oH2.append(data[i].name);
                oDiv.append(oH2);
                //info
                var oInfo=$("<div class='info'></div>");
                //日期
                var oDate=$("<h3 class='h3 my-h3'></h3>");
                oDate.append(data[i]['info'].day);
                oInfo.append(oDate);
                //上传说明
                var oMsg=$("<P class='lead my-lead'></P>");
                oMsg.append(data[i]['info'].msg);
                oInfo.append(oMsg);
                oDiv.append(oInfo);
            }

        }
    })
});



