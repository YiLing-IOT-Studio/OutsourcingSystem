/**
 * Created by 杨玉卿 on 2018/4/15.
 */
//设置AJAX的全局默认选项
$.ajaxSetup( {
    beforeSend: function(xhr) {
        xhr.setRequestHeader('csrf_');
    },
    headers: {
        'csrf_':$("meta[name='_csrf']").attr("content")
    },
    global:true
} );
var btn_sign = $(".btn-sign");
$.ajax({
    type: "get",
    url: "/sign/getSignState",
    dataType: "json",
    data: {},
    success: function (data) {
        if (data == 1) {
            btn_sign.text("签退");
            btn_sign.removeClass("btn-primary");
            btn_sign.addClass("btn-danger");
        }
        else {
            btn_sign.text("签到");
            btn_sign.removeClass("btn-danger");
            btn_sign.addClass("btn-primary");
        }
    },
    error: function () {
        alert("签到请求失败");
    }
});

btn_sign.click(function() {
    var video=document.getElementById('video');
    video.pause();
    $("#video").css("visibility","hidden");
    if (btn_sign.text() == "签到") {
        var signInDate = new Date();
        $.ajax({
            type:"post" ,
            url:"/sign/signIn",
            dataType:"json",
            data:{
                'signInDate': signInDate
            },
            success:function(){
                btn_sign.text("签退");
                btn_sign.removeClass("btn-primary");
                btn_sign.addClass("btn-danger");
            },
            error:function(){
                alert("签到失败！请重试");
            }

        });
    }
    else if (btn_sign.text() == "签退") {

        var signOutDate = new Date();
        $.ajax({
            type:"post" ,
            url:"/sign/signOut",
            dataType:"json",
            data:{
                'signOutDate': signOutDate
            },
            success:function(){
                btn_sign.text("签到");
                btn_sign.removeClass("btn-danger");
                btn_sign.addClass("btn-primary");
            },
            error:function(){
                alert("签退失败！请重试");
            }
        });

    }
});