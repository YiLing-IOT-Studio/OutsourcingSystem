/**
 * Created by YYQ on 2018/3/18.
 */

$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
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
var snap=document.getElementById('snap');
//截取图像
snap.onclick=function(){
    context.drawImage(video,0,0,300,300);
    video.style.display="none";
    canvas.style.display="inline-block";
}


var imgData = canvas.toDataURL();
var upload=$("#upload");
//上传图像
upload.click(function(){
    console.log(imgData);
    $.ajax({
            type: "POST",
            url: '/faceCheck?tag=register',
            dataType: 'json',
            data: {"img": imgData},
            success: function (data) {
                if(data==1){
                    alert("识别成功！");
                }
                else{
                    alert("没有这张脸");
                }
            },
            error: function () {
                alert("请求失败");
            }
        });

});


