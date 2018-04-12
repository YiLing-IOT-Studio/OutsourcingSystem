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
            var clear = "";
            oDiv.html(clear);
            $.each(data, function (index, obj) {
                if (index != (data.length)) {
                    var oFolder = $('<div class="my-inline-block text-center">' + '</div>');
                    oFolder.append('<span class="glyphicon glyphicon-folder-close my-folder"></span>' +
                        '<span class="folder-name">' + obj['project_name'] + '</span>');
                    oDiv.append(oFolder);
                }
            });

            $(".my-inline-block").click(function () {
                var clear = "";
                oDiv.html(clear);
                var project_name = $(this).find(".folder-name").text();
                console.log(project_name);
                $.ajax({
                    type: "POST",
                    url: "/",
                    async: false,
                    dataType: "json",
                    data: {
                        'project_name': project_name
                    },
                    success: function (data) {
                        $.each(data, function (index, obj) {
                            if (index != (data.length)) {
                                var oP = $("<p></p>");
                                //name
                                var oH2 = $("<span></span>");
                                oH2.append(obj['name'] + '&nbsp;&nbsp;&nbsp;&nbsp;');
                                oP.append(oH2);
                                //日期
                                var oDate = $("<span></span>");
                                oDate.append(obj['info'].day + '&nbsp;&nbsp;&nbsp;&nbsp;');
                                oP.append(oDate);
                                //info_上传说明
                                var oMsg = $("<span></span>");
                                oMsg.append(obj['info'].msg + '&nbsp;&nbsp;&nbsp;&nbsp;');
                                oP.append(oMsg);
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


