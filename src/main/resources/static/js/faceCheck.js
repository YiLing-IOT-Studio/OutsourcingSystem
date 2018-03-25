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
var videoM=document.getElementById('videoM');
navigator.mediaDevices.getUserMedia({
    video:true
}).then(function(mediaStream){
    videoM.srcObject=mediaStream;
    videoM.onloadedmetadata=function(){
        videoM.play();
    }
});
var canvas=document.getElementById('canvas');
var context=canvas.getContext('2d');
var snapScreen=document.getElementById('snapScreen');
snapScreen.onclick=function(){
    context.drawImage(videoM,0,0,300,300);
    videoM.style.display="none";
    canvas.style.display="inline-block";
}

var imgData = canvas.toDataURL();
var imgData1 = imgData.substring(22);
var upload=$("#upload");
var username=$.cookie('username');
var flag=$.cookie('flag');
alert('username='+username);
alert('flag='+flag);
upload.click(function(){
    if(flag==0) {
        $.ajax({
            type: 'POST',
            url: '/faceCheck?tag=register',
            dataType: 'json',
            data: {"img": imgData1, "username": username},
            success: function (data) {
                alert("请求成功");
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
                alert("请求成功");
            },
            error: function () {
                alert("请求失败");
            }
        })
    }
})


