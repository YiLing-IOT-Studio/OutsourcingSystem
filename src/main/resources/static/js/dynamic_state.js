/**
 * Created by 杨玉卿 on 2018/4/3.
 */
var oDiv=$("#follow");

//点击动态，显示所有文件夹
$("#follow-tab").click(function(){
    $.ajax({
        type:"POST",
        url:"/dynamicState/getAllOutsourcingName",
        async:false,
        dataType:"json",
        data:{

        },
        success:function(data) {
            var oDiv=$("#follow");
            var clear = "";
            oDiv.html(clear);
            for(var i=0;i<data.length;i++) {

                var oFolder = $('<div class="my-inline-block text-center"></div>');
                oFolder.append('<span class="glyphicon glyphicon-folder-close my-folder"></span>' +
                    '<span class="folder-name">' + data[i] + '</span>');
                oDiv.append(oFolder);

            }



            $(".my-inline-block").click(function () {
                var oDiv=$("#follow");
                var clear = "";
                oDiv.html(clear);
                var project_name = $(this).find(".folder-name").text();
                console.log(project_name);
                $.ajax({
                    type: "POST",
                    url: "/dynamicState/gerDynamicInformation",
                    async: false,
                    dataType: "json",
                    data: {
                        'project_name': project_name
                    },
                    success: function (data) {
                        $.each(data, function (index, obj) {
                            if (index != (data.length)) {
                                var oDiv=$("#follow");
                                var clear = "";
                                oDiv.html(clear);
                                var oP = $("<div class='op'></div>");
                                //name
                                var oH2 = $("<p class='user'><img src='../static/img/emoji" + (parseInt(Math.random() * 5, 10) + 1) + ".png'></p>");
                                oH2.append(''+obj['name'] + '&nbsp;&nbsp;&nbsp;&nbsp;');
                                oP.append(oH2);
                                //info_上传说明
                                var oMsg = $("<p class='description'></p>");
                                oMsg.append(obj['info'].day);
                                oP.append(oMsg);

                                //日期
                                var oDate = $("<p class='day'></p>");
                                oDate.append("时间:"+obj['info'].msg);
                                oP.append(oDate);
                                oDiv.append(oP);
                            }
                        });
                    },
                    error: function () {
                        alert("文件上传信息请求失败!");
                    }
                });
            });
        },
        error:function(){
            alert("文件夹信息请求失败！");
        }
    })
});


