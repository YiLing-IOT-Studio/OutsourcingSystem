/**
 * Created by 杨玉卿 on 2018/4/10.
 */


//通知消息
$("#li_tz_item").click(function(){
    //测试用
//     // alert("1");
//     var oDiv=$("#inform");
//     var clear = "";
//     oDiv.html(clear);
//     var oP = $("<div class='op'></div>");
//
// //title
//     var oTitle=$('<p class="oTitle"></p>');
//     oTitle.append("任务名称："+"前端编写网站");
//     oP.append(oTitle);
// //description
//     var oMsg = $("<p class='description'></p>");
//     oMsg.append("任务描述："+"前端编写网站前端编写网站前端编写网站前端编写网站前端编写网站前端编写网站");
//     oP.append(oMsg);
//     var oUl=$('<ul class="list-inline"></ul>');
//
// //author
//     var oH2 = $("<li><img src='../static/img/emoji" + (parseInt(Math.random() * 5, 10) + 1) + ".png'></li>");
//     oH2.append('<span class="author">'+''+"杨玉卿"+'</span>');
//     oUl.append(oH2);
//
//
//     var oDate = $("<li class='day'></li>");
//     oDate.append("2018/2/5 12:00"+"发布");
//     oUl.append(oDate);
//
//     oP.append(oUl);
// //btn
//     var oBtn=$('<div class="float"></div>');
//     oBtn.append('<button class="btn btn-get">领取任务</button>');
//     oP.append(oBtn);
//
//     oDiv.append(oP);
//     $(".btn-get").click(function(){
//         var taskName=$(this).parent().parent().find('.oTitle').text();
//         alert(taskName);
//     });
    $.ajax({
        type:"POST",
        url:"/inform",
        dataType:"json",
        async: false,
        data:{

        },
        success:function(data){
            //输出data
            var oDiv=$("#inform");
            var clear='';
            oDiv.html(clear);
            for(var i=0;i<data.length;i++) {
                var oFolder = $('<div class="my-inline-block text-center"></div>');
                oFolder.append('<span class="glyphicon glyphicon-folder-close my-folder"></span>' +
                        '<span class="folder-name">' + data[i] + '</span>');
                         oDiv.append(oFolder);
            }
            $(".my-inline-block").click(function () {
                var oDiv=$("#inform");
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
                                var oDiv=$("#inform");
                                var clear = "";
                                oDiv.html(clear);
                                var oP = $("<div class='op'></div>");

                                //title
                                var oTitle=$('<p class="oTitle"></p>');
                                oTitle.append("任务名称："+obj['projectName']);
                                oP.append(oTitle);
                                //description
                                var oMsg = $("<p class='description'></p>");
                                oMsg.append("任务描述："+obj['taskContent']);
                                oP.append(oMsg);
                                var oUl=$('<ul class="list-inline"></ul>');

                                //author
                                var oH2 = $("<li><img src='../static/img/emoji" + (parseInt(Math.random() * 5, 10) + 1) + ".png'></li>");
                                oH2.append('<span class="author">'+''+obj['name']+'</span>');
                                oUl.append(oH2);


                                var oDate = $("<li class='day'></li>");
                                oDate.append(obj['date']+"发布");
                                oUl.append(oDate);

                                oP.append(oUl);
                                //btn
                                var oBtn=$('<div class="float"></div>');
                                oBtn.append('<button class="btn btn-get">领取任务</button>');
                                oP.append(oBtn);

                                oDiv.append(oP);


                            }
                        });
                        $(".btn-get").click(function(){
                            var taskName=$(this).parent().parent().find('.oTitle').text();
                            $.ajax({
                                type: "POST",
                                url: "/",
                                async: false,
                                dataType: "json",
                                data: {
                                    'taskName': taskName
                                },
                                success:function(data){
                                    if(data==1){
                                        alert("您已经成功领到该任务，请到我的任务栏中查看~");
                                    }
                                    else if(data==2){
                                        alert("正在向管理员申请中...申请成功后将在'我的任务'栏显示您的任务");
                                    }
                                },
                                error:function(){
                                    alert("发送领取任务请求失败");
                                }
                            })
                        });

                    },
                    error: function () {
                        alert("请求任务信息失败!");
                    }
                });
            });

        },
        error:function(){
            alert("请求项目名失败！");
        }
    })
});