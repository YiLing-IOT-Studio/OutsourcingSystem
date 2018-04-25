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
            if(data.length==0){
                oDiv.html("<div class='alert alert-warning'>还没创建外包项目？点击‘发布新外包’栏即可创建属于您的新外包项目！</div>");
            }
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
                        var oDiv=$("#follow");
                        var clear = "";
                        oDiv.html(clear);
                        if(data.length==0){
                            oDiv.html("<div class='alert alert-warning'>暂无上传信息，您可以尝试点击‘发布任务’栏，发布外包具体任务或稍后查看项目动态</div>");
                        }
                        $.each(data, function (index, obj) {
                            if (index != (data.length)) {

                                var oP = $("<div class='op'></div>");
                                //name
                                var oH2 = $("<p class='user'><img src='img/emoji" + (parseInt(Math.random() * 5, 10) + 1) + ".png'></p>");
                                oH2.append('<span class="author">'+''+obj['name']+'</span>');
                                oP.append(oH2);
                                //info_上传说明
                                var oMsg = $("<p class='description'></p>");
                                oMsg.append(obj['info'].msg);
                                oP.append(oMsg);

                                //日期
                                var oDate = $("<p class='day'></p>");
                                oDate.append("时间:"+obj['info'].day);
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


