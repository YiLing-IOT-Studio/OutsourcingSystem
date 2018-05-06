/**
 * Created by 杨玉卿 on 2018/4/10.
 */
var canvas=document.getElementById('canvas');
var context=canvas.getContext("2d");
//截取图像
function snapPicture(){
    var video=document.getElementById('video');
    $("#video").css("visibility","visible");
    context.drawImage(video,0,0,100,100);
    canvas.style.display="none";
    // var canvas1=$("#canvas");
    // canvas1.css("display")
    var imgData = canvas.toDataURL();
    //获取图像在前端截取22位以后的字符串作为图像数据
    var imgData1 = imgData.substring(22);

    console.log(username);
    $.ajax({
            type: "POST",
            url: '/sendFaceToLookRes',
            dataType: 'json',
            async:false,
            data: {"img": imgData1,

            },
            success: function (data) {
                var put_word=$("#data");
                if (data == 1) {
                }
                else {
                    put_word.html("<h2 class='col-center-block'>检测到第三方人脸~停止访问</h2>");
                }
            },
            error: function () {
                alert("请求失败");
            }
        });


}
var time1;
//资料库
$("#li_zl_item").click(function(){
    window.clearInterval(time1);

    var video=document.getElementById('video');
    video.pause();
    $("#video").css("visibility","hidden");

    $.ajax({
        type:"POST",
        url:"/myTask/showOutsourcingInfo",
        dataType:"json",
        async: false,
        data:{

        },
        success:function(data){
            //输出文件名，显示文件夹
            var oDiv=$("#data");
            var clear='';
            oDiv.html(clear);
            if(data.length==0){
                oDiv.html("<div class='alert alert-warning'>您可能还没有接包或审批未通过，暂无外包信息通知</div>");
            }
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
                    url:"/myTask/getMyTask",
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
                        if(data.length==0){
                            oDiv.html("<div class='alert alert-warning'>还没有任务信息发布，请耐心等待</div>");
                        }
                        for(var i=0;i<data.length;i++) {
                            var oFolder = $('<div class="my-inline-block1"></div>');
                            oFolder.append('<span class="glyphicon glyphicon-folder-close my-folder"></span>' +
                                '<span class="folder-name1">' + data[i].taskName + '</span>');
                            oDiv.append(oFolder);
                        }
                        //点击任务夹，显示资料夹
                        $(".my-inline-block1").click(function(){
                            var task_name=$(this).find(".folder-name1").text();
                            $.ajax({
                                type:"POST",
                                url:"/getTaskRank",
                                dataType:"json",
                                async: false,
                                data:{
                                    'projectName':project_name,
                                    'task_name':task_name
                                },
                                success:function(data){
                                    //显示资料夹
                                    var oDiv=$("#data");
                                    var clear='';
                                    oDiv.html(clear);
                                    if(data.length==0){
                                        oDiv.html("<div class='alert alert-warning'>还没有任务资料上传，请耐心等待</div>");
                                    }
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
                                    if(data==3){

                                        //打开摄像头
                                        var video1=$('#video');
                                        video1.css("visibility","visible");
//打开摄像头
                                        var video=document.getElementById('video');
                                        navigator.mediaDevices.getUserMedia({
                                            video:true
                                        }).then(function(mediaStream){
                                            video.srcObject=mediaStream;
                                            console.log(video.srcObject);
                                            video.onloadedmetadata=function() {
                                                video.play();

                                            }
                                        });

                                        time1=setInterval(snapPicture,3000);

                                    }
                                    //点击资料夹，打开资料
                                   $(".my-inline-block2").click(function(){
                                       var category=$(this).find(".folder-name2").text();
                                       if(category=='图片'){
                                           $.ajax({
                                               type:"POST",
                                               url:"/getResource?category=picture",
                                               dataType:"json",
                                               async: false,
                                               data:{
                                                   'projectName':project_name
                                               },
                                               success:function(data){
                                                   var oDiv=$("#data");
                                                   var clear='';
                                                   oDiv.html(clear);
                                                   if(data.length==0){
                                                       oDiv.html("<div class='alert alert-warning'>无上传图片资料</div>");
                                                   }
                                                   //图片
                                                   for(var i in data){
                                                       var src=data[i];
                                                        alert(src);
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
                                               url:"/getResource?category=video",
                                               dataType:"json",
                                               async: false,
                                               data:{
                                                   'projectName':project_name
                                               },
                                               success:function(data){
                                                   var oDiv=$("#data");
                                                   var clear='';
                                                   oDiv.html(clear);
                                                   //视频
                                                   if(data.length==0){
                                                       oDiv.html("<div class='alert alert-warning'>无上传视频资料</div>");
                                                   }
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
                                               url: "/getResource?category=document",
                                               dataType: "json",
                                               async: false,
                                               data: {
                                                   'projectName':project_name
                                               },
                                               success: function (data) {
                                                   var oDiv = $("#data");
                                                   var clear = '';
                                                   oDiv.html(clear);
                                                   if(data.length==0){
                                                       oDiv.html("<div class='alert alert-warning'>无上传文档资料</div>");
                                                   }
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


