/**
 * Created by 杨玉卿 on 2018/4/15.
 */
$(".btn-sign").click(function(){
    var this_btn=$(this);
    if(this_btn.text()=="今日签到"||this_btn.text()=="签到"){
        this_btn.text("签退");
        var signInDate=new Date();
        $.post("/",{'signInDate':signInDate});

    }
    else if(this_btn.text()=="签退"){
        this_btn.text("签到");
        var signOutDate=new Date();
        $.post("/",{'signOutDate':signOutDate});
    }
});