/**
 * Created by 杨玉卿 on 2018/4/10.
 */
// $(".my-inline-block").click(function(){
//     $(".file").removeClass("hide");
//     $(".my-inline-block").addClass("hide");
// })

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
                var put_word=$("#data");
                if (data == 1) {
                }
                else {
                    put_word.html("检测到第三方人脸~停止访问");
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
            //输出文件名，显示文件夹
            var oDiv=$("#data");
            var clear='';
            oDiv.html(clear);
            for(var i=0;i<data.length;i++) {
                var oFolder = $('<div class="my-inline-block text-center"></div>');
                oFolder.append('<span class="glyphicon glyphicon-folder-close my-folder"></span>' +
                    '<span class="folder-name">' + data[i] + '</span>');
                oDiv.append(oFolder);
            }
            //点击文件夹，显示任务夹
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
                        //显示任务夹
                        var oDiv=$("#data");
                        var clear='';
                        oDiv.html(clear);
                        for(var i=0;i<data.length;i++) {
                            var oFolder = $('<div class="my-inline-block1 text-center"></div>');
                            oFolder.append('<span class="glyphicon glyphicon-folder-close my-folder"></span>' +
                                '<span class="folder-name1">' + data[i] + '</span>');
                            oDiv.append(oFolder);
                        }
                        //点击任务夹，显示资料夹
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
                                    //显示资料夹
                                    var oDiv=$("#data");
                                    var clear='';
                                    oDiv.html(clear);
                                    var oFolder1 = $('<div class="my-inline-block2 text-center"></div>');
                                    oFolder1.append('<span class="glyphicon glyphicon-folder-open my-folder"></span>' +
                                        '<span class="folder-name2">' + '图片' + '</span>');
                                    oDiv.append(oFolder1);
                                    var oFolder2 = $('<div class="my-inline-block2 text-center"></div>');
                                    oFolder2.append('<span class="glyphicon glyphicon-folder-open my-folder"></span>' +
                                        '<span class="folder-name2">' + '视频' + '</span>');
                                    oDiv.append(oFolder2);
                                    var oFolder3 = $('<div class="my-inline-block2 text-center"></div>');
                                    oFolder3.append('<span class="glyphicon glyphicon-folder-open my-folder"></span>' +
                                        '<span class="folder-name2">' + '文档' + '</span>');
                                    oDiv.append(oFolder3);
                                    //返回权限为高级，打开摄像头
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
                                    //点击资料夹，打开资料
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

                                               },
                                               error:function(){
                                                   alert("请求图片失败");
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

                                               },
                                               error:function(){
                                                   alert("请求视频失败");
                                               }
                                           });
                                       }
                                       if(category=='文档'){
                                           $.ajax({
                                               type: "POST",
                                               url: "/",
                                               dataType: "json",
                                               async: false,
                                               data: {
                                                   'category': category
                                               },
                                               success: function (data) {
                                                   var oDiv = $("#data");
                                                   var clear = '';
                                                   oDiv.html(clear);
                                                   for (var i = 0; i < data.length; i++) {
                                                       var oFolder3 = $('<div class="my-inline-block3 text-center"></div>');
                                                       oFolder3.append('<span class="glyphicon glyphicon-folder-file my-folder"></span>' +
                                                           '<span class="folder-name3">' + data[i] + '</span>');
                                                       oDiv.append(oFolder3);
                                                   }
                                                   //点击下载
                                               },
                                               error:function(){
                                                   alert("请求文档失败");
                                               }
                                           })
                                       }
                                   })


                                },
                                error:function(){
                                    alert("请求资料夹失败");
                                }
                            });

                        })

                    },
                    error:function(){
                        alert("请求任务名失败");
                    }
                });
            });

        },
        error:function(){
            alert("请求项目名失败！");
        }
    })
});


