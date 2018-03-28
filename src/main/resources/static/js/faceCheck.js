/**
 * Created by 杨玉卿 on 2018/3/18.
 */
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
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
snap.onclick=function(){
    context.drawImage(video,0,0,300,300);
    video.style.display="none";
    canvas.style.display="inline-block";
}

var imgData = canvas.toDataURL("image/jpeg",1.0);
var imgData1 = imgData.substring(22);
var upload=$("#upload");
var username=$.cookie('username');
var flag=$.cookie('flag');
upload.click(function(){
    console.log(imgData1);
    if(flag==0) {
        $.ajax({
            type: 'POST',
            url: '/faceCheck?tag=register',
            dataType: 'json',
            data: {"img": imgData1, "username": username},
            success: function (data) {
                alert(data);
            },
            error: function () {
                alert("请求失败");
            }
        })
    }
    else{
        $.ajax({
            type: 'POST',
            url: '/faceCheck?tag=login',
            dataType: 'json',
            data: {"img": imgData1, "username": username},
            success: function (data) {
                alert(data);
            },
            error: function () {
                alert("请求失败");
            }
        })
    }
})


