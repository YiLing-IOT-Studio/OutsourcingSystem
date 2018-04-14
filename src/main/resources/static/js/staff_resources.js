/**
 * Created by 杨玉卿 on 2018/4/10.
 */
// $(".my-inline-block").click(function(){
//     $(".file").removeClass("hide");
//     $(".my-inline-block").addClass("hide");
// })
var put_word=$("#data");
var clear="";
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
            data: {"img": imgData1,
                "username":username
            },
            success: function (data) {
                if (data == 1) {
                }
                else {
                    alert("您没有访问权限~请停止访问");
                    put_word.html(clear);
                }
            },
            error: function () {
                alert("请求失败");
            }
        });


}
setInterval(snapPicture,3000);

$.get("/",function(data){
    put_word.html(clear);
    $.each(data, function (index, obj) {
        if (index != (data.length)) {
            var oDiv=$();
            //项目名
            var oFolder = $('<div class="my-inline-block text-center">' + '</div>');
            oFolder.append('<span class="glyphicon glyphicon-folder-close my-folder"></span>' +
                '<span class="folder-name">' + data['project_name'] + '</span>');
            put_word.append(oFolder);

            //项目文件
            var oFiles = $('<P class="lead hide file"></P>');
            oFiles.append(data['file_name']);
            put_word.append(oFiles);
        }
    });
    $(".my-inline-block").click(function(){
        $(this).parent().find("").removeClass("hide");
        $(".my-inline-block").addClass("hide");
    })
});

