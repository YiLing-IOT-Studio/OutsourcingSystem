/**
 * Created by 杨玉卿 on 2018/4/3.
 */
var oDiv=$("#follow");
var pageNav=$("#pagi-father");

//点击动态，显示所有文件夹
$("#follow-tab").click(function(){
    pageNav.hide();
    $.ajax({
        type:"POST",
        url:"/dynamicState/getAllOutsourcingName",
        async:false,
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

$(".inline-block").click(function(){
    pageNav.show();
    var name=$(this).find(".folder-name").text();
    console.log(name);

    ajaxTest(1,name);

});
//上传的信息、分页类
function ajaxTest(num,name){
    $.ajax({
        type:"POST",
        url:"/dynamicState/gerDynamicInformation",
        async:false,
        dataType:"json",
        data:{
            "rows":"10",
            "pageNo":num,
            "name":name
        },
        success:function(data){
            //输出data
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
            scrollTo(0,0);//回到顶部

            //分页
            $("#page").paging({
                pageNo: num,
                totalPage:data[data.length - 1]['totalPage'],//总页数
                totalSize:data[data.length - 1]['totalSize'],//总记录数
                callback: function(num) {
                    ajaxTest(num);
                }
            })
        },
        error:function(){
            alert("请求失败！");
        }
    })
};


