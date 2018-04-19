/**
 * Created by 杨玉卿 on 2018/4/15.
 */
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(header, token);
});
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
var video=document.getElementById('video');
video.pause();
$("#video").css("visibility","hidden");
$(".btn-sign").click(function() {
    var btn_sign = $(".btn-sign");



    if (btn_sign.text() == "签到") {
        btn_sign.text("签退");
        btn_sign.removeClass("btn-primary");
        btn_sign.addClass("btn-danger");
        var signInDate = new Date();
        $.post("/sign/signIn", {'signInDate': signInDate});

    }
    else if (btn_sign.text() == "签退") {
        btn_sign.text("签到");
        btn_sign.removeClass("btn-danger");
        btn_sign.addClass("btn-primary");
        var signOutDate = new Date();
        $.post("/sign/signOut", {'signOutDate': signOutDate});
    }
});