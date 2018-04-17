/**
 * Created by 杨玉卿 on 2018/4/15.
 */
var btn_sign=$(".btn-sign");
$.ajax({
    type:"get",
    url:"/",
    dataType:"json",
    data:{},
    success:function(data){
        if(data==1){
            btn_sign.text("签退");
            btn_sign.removeClass("btn-danger");
            btn_sign.addClass("btn-primary");
        }
        else{
            btn_sign.text("签到");
            btn_sign.removeClass("btn-primary");
            btn_sign.addClass("btn-danger");
        }
    },
    error:function(){
        alert("error");
    }
});

btn_sign.click(function(){
    var video=document.getElementById('video');
    video.pause();
    $("#video").css("visibility","hidden");
    var this_btn=$(this);
    if(this_btn.text()=="签到"){
        this_btn.text("签退");
        this_btn.removeClass("btn-danger");
        this_btn.addClass("btn-primary");
        var signInDate=new Date();
        $.post("/",{'signInDate':signInDate});

    }
    else if(this_btn.text()=="签退"){
        this_btn.text("签到");
        this_btn.removeClass("btn-primary");
        this_btn.addClass("btn-danger");
        var signOutDate=new Date();
        $.post("/",{'signOutDate':signOutDate});
    }
});