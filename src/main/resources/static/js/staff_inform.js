/**
 * Created by 杨玉卿 on 2018/4/10.
 */


//通知消息
$("#li_tz_item").click(function(){
    var video=document.getElementById('video');
    video.pause();
    $("#video").css("visibility","hidden");
    //测试用
//     // alert("1");
//     var oDiv=$("#inform");
//     var clear = "";
//     oDiv.html(clear);
//     var oP = $("<div class='op'></div>");
//
// //title
//     var oTitle=$('<p class="oTitle"></p>');
//     oTitle.append(" 任务名称："+"前端编写网站"+'');
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
        url:"/receiveTask/showOutsourcingInfo",
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
                    url: "/receiveTask/showTaskInfo",
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
                                var oTitle=$('<p></p>');
                                oTitle.append(" 任务名称："+"<span class='oTitle'>"+obj['taskName']+"</span>");
                                oP.append(oTitle);

                                //description
                                var oMsg = $("<p class='description'></p>");
                                oMsg.append("任务描述："+obj['taskContent']);
                                oP.append(oMsg);
                                //author
                                var oUl=$('<ul class="list-inline"></ul>');


                                var oH2 = $("<li><img src='img/emoji" + (parseInt(Math.random() * 5, 10) + 1) + ".png'></li>");
                                oH2.append('<span class="author">'+''+obj['promulgator']+'</span>');
                                oUl.append(oH2);


                                var oDate = $("<li class='day'></li>");
                                oDate.append("发布时间：" + obj['releaseTime']);
                                oUl.append(oDate);

                                var oDeadline=$("<li class='deadline'></li>");
                                oDeadline.append("截止时间：" + obj['missionDeadLine']);
                                oUl.append(oDeadline);

                                oP.append(oUl);
                                //btn
                                var oBtn=$('<div class="float"></div>');
                                oBtn.append('<button class="btn btn-get">领取任务</button>');
                                oP.append(oBtn);

                                //项目
                                var oInfo=$('<div class="hide"></div>');
                                oInfo.append(obj['projectName']);
                                oP.append(oInfo);

                                oDiv.append(oP);


                            }
                        });
                        $(".btn-get").click(function(){
                            var taskName=$(this).parent().parent().find('.oTitle').text();
                            var projectName2=$(this).parent().parent().find('.hide').text();
                            console.log(projectName2);
                            $.ajax({
                                type: "POST",
                                url: "/receiveTask/getTask",
                                async: false,
                                dataType: "json",
                                data: {
                                    'taskName': taskName,
                                    'projectName2':projectName2
                                },
                                success:function(data){
                                    if(data==1){
                                        alert("任务申请成功，请等待审核");
                                    }
                                    else if(data==2){
                                        alert("任务正在申请中，请等待审核");
                                    }
                                    else if (data==3){
                                        alert("您已成功领取该任务");
                                    }
                                    else{
                                        alert("任务申请失败");
                                    }
                                },
                                error:function(){
                                    alert("领取任务请求失败，请重试");
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