//本地测试
var faceCheckPath = "http://localhost:8080/faceCheck";
var indexPath = "http://localhost:8080/index";
var registerSuccess = "http://localhost:8080/login_register";

//上线环境
// var faceCheckPath = "https://www.zhyocean.cn:8080/faceCheck";
// var indexPath = "https://www.zhyocean.cn:8080/index";
// var registerSuccess = "https://www.zhyocean.cn:8080/login_register";

/**
 * Created by YYQ on 2018/3/18.
 */
$.ajaxSetup( {
    beforeSend: function(xhr) {
        xhr.setRequestHeader('X-CSRF-TOKEN',$("meta[name='_csrf']").attr("content"));
    },
    global:true
} );
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
    var imgData = canvas.toDataURL();
    //获取图像在前端截取22位以后的字符串作为图像数据
    var imgData1 = imgData.substring(22);
    var username=$.cookie("username");
    var flag=$.cookie("flag");
    if(flag==0) {
        $.ajax({
            type: "POST",
            url: '/faceCheck?tag=register',
            dataType: 'json',
            data: {"img": imgData1,
                "username":username
            },
            success: function (data) {
                if (data == 1) {
                    alert("注册成功！");
                    // window.location.replace(registerSuccess)
                    window.location.replace(registerSuccess)
                }
                else {
                    alert("注册失败，请重试~");
                    // window.location.replace(faceCheckPath)
                     window.location.replace(faceCheckPath)

                }
            },
            error: function () {
                alert("请求失败");
            }
        });
    }
    else{
        $.ajax({
            type: "POST",
            url: '/faceCheck?tag=login',
            dataType: 'json',
            data: {"img": imgData1,
                "username":username
            },
            success: function (data) {
                if (data == 1) {
                    alert("登录成功！");
                     // window.location.replace(indexPath)
                   window.location.replace(indexPath)
                }
                else {
                    alert("没有这张脸，请重试！");
                    // window.location.replace(faceCheckPath)
                    window.location.replace(faceCheckPath)
                }
            },
            error: function () {
                alert("请求失败");
            }
        });
    }
}


