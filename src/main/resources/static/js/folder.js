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
            $.each(data, function (index, obj) {
                if (index != (data.length)) {
                    var oFolder = $('<div class="my-inline-block text-center">' + '</div>');
                    oFolder.append('<span class="glyphicon glyphicon-folder-close my-folder"></span>' +
                        '<span class="folder-name">' + obj['project_name'] + '</span>');
                    oDiv.append(oFolder);
                }
            });
            $(".my-inline-block").click(function(){
                var clear="";
                oDiv.html(clear);
                var project_name=$(this).find(".folder-name").text();
                console.log(project_name);
                //name
                var oH2=$("<h2 class='leader my-h2'></h2>");
                oH2.append(data[my_index]['staff_name']);
                oDiv.append(oH2);
                //info
                var oInfo=$("<div class='info'></div>");
                //日期
                var oDate=$("<h3 class='h3 my-h3'></h3>");
                oDate.append(data[my_index]['info'].day);
                oInfo.append(oDate);
                //上传说明
                var oMsg=$("<P class='lead my-lead'></P>");
                oMsg.append(data[my_index]['info'].msg);
                oInfo.append(oMsg);
                oDiv.append(oInfo);
            });

        },
        error:function(){
            alert("请求失败！");
        }
    })
});


