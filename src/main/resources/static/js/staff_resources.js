/**
 * Created by 杨玉卿 on 2018/4/10.
 */
// $(".my-inline-block").click(function(){
//     $(".file").removeClass("hide");
//     $(".my-inline-block").addClass("hide");
// })
var put_word=$("#data");
var clear="";

var canvas=document.getElementById('canvas');
var context=canvas.getContext("2d");
//截取图像
function snapPicture(){
    context.drawImage(video,0,0,100,100);
    video.style.display="none";
    canvas.style.display="inline-block";
    var imgData = canvas.toDataURL();
    //获取图像在前端截取22位以后的字符串作为图像数据
    var imgData1 = imgData.substring(22);
    var username=$.cookie("username");
    console.log(username);
    $.ajax({
            type: "POST",
            url: '/',
            dataType: 'json',
            async:false,
            data: {"img": imgData1,
                "username":username
            },
            success: function (data) {
                if (data == 1) {
                }
                else {
                    put_word.html("检测到第三方人脸~请停止访问");
                }
            },
            error: function () {
                alert("请求失败");
            }
        });


}

//资料库
$("#li_zl_item").click(function(){
    $.ajax({
        type:"POST",
        url:"/",
        dataType:"json",
        async: false,
        data:{

        },
        success:function(data){
            //输出文件名
            var oDiv=$("#data");
            var clear='';
            oDiv.html(clear);
            for(var i=0;i<data.length;i++) {
                var oFolder = $('<div class="my-inline-block text-center"></div>');
                oFolder.append('<span class="glyphicon glyphicon-folder-close my-folder"></span>' +
                    '<span class="folder-name">' + data[i] + '</span>');
                oDiv.append(oFolder);
            }

            $(".my-inline-block").click(function () {
                var oDiv=$("#data");
                var clear = "";
                oDiv.html(clear);
                var project_name = $(this).find(".folder-name").text();
                $.ajax({
                    type:"POST",
                    url:"/",
                    dataType:"json",
                    async: false,
                    data:{
                        'project_name':project_name
                    },
                    success:function(data){
                        //输出任务名
                        var oDiv=$("#data");
                        var clear='';
                        oDiv.html(clear);
                        for(var i=0;i<data.length;i++) {
                            var oFolder = $('<div class="my-inline-block1 text-center"></div>');
                            oFolder.append('<span class="glyphicon glyphicon-folder-close my-folder"></span>' +
                                '<span class="folder-name1">' + data[i] + '</span>');
                            oDiv.append(oFolder);
                        }
                        $(".my-inline-block1").click(function(){
                            var task_name=$(this).find(".folder-name1").text();
                            $.ajax({
                                type:"POST",
                                url:"/",
                                dataType:"json",
                                async: false,
                                data:{
                                    'task_name':task_name
                                },
                                success:function(data){
                                    var oDiv=$("#data");
                                    var clear='';
                                    oDiv.html(clear);
                                    //图片、视频
                                    var oFolder1 = $('<div class="my-inline-block2 text-center"></div>');
                                    oFolder1.append('<span class="glyphicon glyphicon-folder-open my-folder"></span>' +
                                        '<span class="folder-name2">' + '图片' + '</span>');
                                    oDiv.append(oFolder1);
                                    var oFolder2 = $('<div class="my-inline-block2 text-center"></div>');
                                    oFolder2.append('<span class="glyphicon glyphicon-folder-open my-folder"></span>' +
                                        '<span class="folder-name2">' + '视频' + '</span>');
                                    oDiv.append(oFolder2);
                                   if(data['authority']==3){
                                       //打开摄像头
                                       var video=document.getElementById('video');
                                       navigator.mediaDevices.getUserMedia({
                                           video:true
                                       }).then(function(mediaStream){
                                           video.srcObject=mediaStream;
                                           video.onloadedmetadata=function(){
                                               video.play();
                                           }
                                       });

                                       setInterval(snapPicture,3000);
                                   }
                                   $(".my-inline-block2").click(function(){
                                       var category=$(this).find(".folder-name2").text();
                                       if(category=='图片'){
                                           $.ajax({
                                               type:"POST",
                                               url:"/",
                                               dataType:"json",
                                               async: false,
                                               data:{
                                                   'category':category
                                               },
                                               success:function(data){
                                                   var oDiv=$("#data");
                                                   var clear='';
                                                   oDiv.html(clear);
                                                   //图片
                                                   for(var i in data){
                                                       var src=data[i];
                                                       var oImg=$("<img src='"+src+"' class='picture'/>");
                                                       oDiv.append(oImg);
                                                   }

                                               }
                                           });
                                       }
                                       if(category=='视频'){
                                           $.ajax({
                                               type:"POST",
                                               url:"/",
                                               dataType:"json",
                                               async: false,
                                               data:{
                                                   'category':category
                                               },
                                               success:function(data){
                                                   var oDiv=$("#data");
                                                   var clear='';
                                                   oDiv.html(clear);
                                                   //图片
                                                   for(var i in data){
                                                       var src=data[i];
                                                       var oVideo=$("<video src='"+src+"' class='video'></video>");
                                                       oVideo.append(oImg);
                                                   }

                                               }
                                           });
                                       }

                                   })


                                }
                            });

                        })

                    },
                    error:function(){
                        alert("请求任务名失败");
                    }
                });

                console.log(project_name);

                $(".my-inline-block2").click(function(){
                    var oDiv=$("#data");
                    var clear = "";
                    oDiv.html(clear);
                    category_name = $(this).find(".folder-name2").text();
                    $.ajax({
                        type: "POST",
                        url: "/",
                        async: false,
                        dataType: "json",
                        data: {
                            'project_name': project_name,
                            'category_name':category_name
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
                                    oTitle.append(" 任务名称："+obj['taskName']);
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

            });

        },
        error:function(){
            alert("请求项目名失败！");
        }
    })
});

