$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
//隐藏错误提示和标志符号
$('.help-block').hide();
$(".form-control-feedback").hide();

    //获取输入框内容长度函数
    function getLen($id) {
            var myValue = $('#' + $id).val();
            return len = myValue.length;
    }

    //隐藏错误提示和标志符号
    $('.help-block').hide();
    $(".form-control-feedback").hide();

    //注册手机号码验证
    var phone1 = $("#phone1");
    var phone1_f = $("#phone1_f");
    var phone1_t = $("#phone1_t");
    var phone1_error = $("#phone1_error");
    var phone1_null = $("#phone1_null");
    var phone1_box = $("#telephone1");
    var phone1_existed = $("#phone1-existed");
    phone1.keyup(function () {
        //获得手机号的长度，验证长度是否为空
        var phone1_len = getLen('phone1');
        if (phone1_len == 0) {
            phone1_error.hide();
            phone1_t.hide();
            phone1_existed.hide();
            phone1_f.show();
            phone1_null.show();
            phone1_box.remove("has-success");
            phone1_box.addClass("has-error");
        }
        //获得手机号的内容，验证值是否合法
        var phone1_value = phone1.val();
        var pattern;
        pattern = /^1[345789]\d{9}$/;
        if (phone1_len !== 0) {
            phone1_null.hide();

            {
                if (!pattern.test(phone1_value)) {
                    phone1_error.show();
                    phone1_f.show();
                    phone1_t.hide();
                    phone1_existed.hide();
                    phone1_box.removeClass("has-success");
                    phone1_box.addClass("has-error");
                }
                if (pattern.test(phone1_value)) {
                    //获得手机号的值，验证是否已经注册
                    $.ajax({
                        type: "POST",
                        url: "/checkRegisterPhone",
                        dataType: 'json',
                        data: {
                            "register_phone": phone1_value
                        },
                        success: function (data) {
                            //返回0，已经注册
                            if (parseInt(data) === 0) {
                                phone1_existed.show();
                                phone1_f.show();
                                phone1_box.removeClass("has-success");
                                phone1_box.addClass("has-error");
                                phone1_error.hide();
                                phone1_t.hide();
                            }
                            //返回1，没有注册
                            else if (parseInt(data) === 1) {
                                phone1_f.hide();
                                phone1_error.hide();
                                phone1_t.show();
                                phone1_existed.hide();
                                phone1_box.removeClass("has-error");
                                phone1_box.addClass("has-success");
                            }
                            //数据异常
                            else{
                                alert("返回数据异常!");
                                phone1_existed.hide();
                                phone1_f.show();
                                phone1_box.removeClass("has-success");
                                phone1_box.addClass("has-error");
                                phone1_error.hide();
                                phone1_t.hide();
                            }
                        },
                        error:function(){
                            alert("请求失败!");
                            phone1_existed.hide();
                            phone1_f.show();
                            phone1_box.removeClass("has-success");
                            phone1_box.addClass("has-error");
                            phone1_error.hide();
                            phone1_t.hide();
                        }
                    });
                }
            }
        }
    });
    //绑定btn按钮的监听事件
    var msg_btn = $("#msg_btn");
    // 定义发送时间间隔(s)
    var my_interval;
    my_interval = 60;
    var timeLeft = my_interval;
    //重新发送计时函数
    var timeCount = function() {
        window.setTimeout(function() {
            if(timeLeft > 0) {
                timeLeft -= 1;
                msg_btn.html(timeLeft + "后重新发送");
                timeCount();
            } else {
                msg_btn.html("重新发送");
                $("#msg_btn").attr('disabled',false);
            }
        }, 1000);
    };
    //点击发送短信验证码
    msg_btn.click(function () {
        msg_btn.attr('disabled', true);
        var phone1_box = $("#telephone1");
        if (phone1_box.hasClass("has-success")) {
            $.ajax({
                type: "POST",
                url: "/sendMsgCode",
                dataType: 'json',
                data: {
                    "phone1": $("#phone1").val()
                },
                success: function (data) {
                    //返回1，成功发送验证码
                    if (parseInt(data) == 1) {
                        alert("验证码已发送至您输入的手机号！有效期5分钟");
                        timeLeft = my_interval;
                        timeCount();
                    }
                    else {
                        alert("验证码获取失败！请重新获取");
                        $("#msg_btn").attr('disabled',false);
                    }
                },
                error: function () {
                    alert("请求失败！");
                }
            });
        }
        else {
            alert("请先正确填写手机号！");
            $("#msg_btn").attr('disabled',false);
        }
    });

    //验证短信验证码函数
    var msg_code = $("#msg_code");
    var msg_box = $("#msg_box");
    var msg_f = $("#msg_f");
    var msg_t = $("#msg_t");
    var msg_error = $("#msg_error");
    var msg_null = $("#msg_null");

    msg_code.keyup(function () {
        var msg_len1 = getLen('msg_code');
        if (msg_len1 == 0) {
            msg_error.hide();
            msg_f.show();
            msg_t.hide();
            msg_null.show();
            msg_box.removeClass("has-success");
            msg_box.addClass("has-error");
        }
        var msg_len2 = getLen('msg_code');
        if (msg_len2 !== 0) {
            msg_null.hide();
            {
                //ajax
                $.ajax({
                    type: "POST",
                    url: "/checkMsgCode",
                    dataType: 'json',
                    data: {
                        "register_phone": $("#phone1").val(),
                        "msgCode": $("#msg_code").val()
                    },
                    success:function(data){
                        //返回0,说明验证码和手机号不匹配
                        if(parseInt(data)===0){
                            msg_error.show();
                            msg_f.show();
                            msg_t.hide();
                            msg_box.removeClass("has-success");
                            msg_box.addClass("has-error");
                        }
                        //返回1,说明验证码和手机号匹配
                        else if(parseInt(data)===1){
                            msg_box.removeClass("has-error");
                            msg_box.addClass("has-success");
                            msg_error.hide();
                            msg_f.hide();
                            msg_t.show();
                        }
                        else{
                            alert("数据异常！");
                            msg_error.hide();
                            msg_f.show();
                            msg_t.hide();
                            msg_box.removeClass("has-success");
                            msg_box.addClass("has-error");
                        }
                    },
                    error:function()
                    {
                        alert("验证请求失败!");
                        msg_error.hide();
                        msg_f.show();
                        msg_t.hide();
                        msg_box.removeClass("has-success");
                        msg_box.addClass("has-error");
                    }

                });
            }
        }
    });
    //昵称验证
    var myName=$("#myName");
    var name_box=$("#name_box");
    var name_f=$("#name_f");
    var name_t=$("#name_t");
    var name_error=$("#name_error");
    var name_null=$("#name_null");
myName.keyup(function () {
        var name_len1 = getLen('myName');
        if (name_len1 == 0) {
            name_error.hide();
            name_t.hide();
            name_f.show();
            name_null.show();
            name_box.remove("has-success");
            name_box.addClass("has-error");
        }
        var name_value = myName.val();
        var name_len2 = getLen('myName');
        var pattern = /([A-Za-z0-9]{2,20})|([\u4e00-\u9fa5]{2,10})|([\u4e00-\u9fa5][\w\W]{2})/;
        if (name_len2 !== 0) {
            name_null.hide();
            {
                if (!pattern.test(name_value)) {
                    name_error.show();
                    name_f.show();
                    name_t.hide();
                    name_box.removeClass("has-success");
                    name_box.addClass("has-error");
                }
                if (pattern.test(name_value)) {
                    name_box.removeClass("has-error");
                    name_box.addClass("has-success");
                    name_error.hide();
                    name_f.hide();
                    name_t.show();


                }
            }
        }
    });
    //密码验证
    var psw1=$("#psw1");
    var psw1_box=$("#psw1_box");
    var psw1_null=$("#psw1_null");
    var psw1_error=$("#psw1_error");
    var psw1_f=$("#psw1_f");
    var psw1_t=$("#psw1_t");
    var psw1_value;
    $(".psw").keyup(function () {
        var psw1_len1 = getLen('psw1');
        if (psw1_len1 == 0) {
            psw1_error.hide();
            psw1_null.show();
            psw1_t.hide();
            psw1_f.show();
            psw1_box.removeClass("has-success");
            psw1_box.addClass("has-error");
        }
        psw1_value =psw1.val();
        var psw1_len2 = getLen('psw1');
        var pattern = /(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{6,16}$/;
        if (psw1_len2 !== 0) {
            psw1_null.hide();
            {
                if (!pattern.test(psw1_value)) {
                    psw1_t.hide();
                    psw1_error.show();
                    psw1_f.show();
                    psw1_box.removeClass("has-success");
                    psw1_box.addClass("has-error");
                }
                if (pattern.test(psw1_value)) {
                    psw1_box.removeClass("has-error");
                    psw1_error.hide();
                    psw1_f.hide();
                    psw1_t.show();
                    psw1_box.addClass("has-success");
                }
            }
        }
    });
    //确认密码验证
    var psw2=$("#psw2");
    var psw2_box=$("#psw2_box");
    var psw2_null=$("#psw2_null");
    var psw2_error=$("#psw2_error");
    var psw2_f=$("#psw2_f");
    var psw2_t=$("#psw2_t");
    $(".psw").keyup(function () {
        var psw2_len1 = getLen('psw2');
        if (psw2_len1 == 0) {
            psw2_error.hide();
            psw2_f.show();
            psw2_t.hide();
            psw2_null.show();
            psw2_box.removeClass("has-success");
            psw2_box.addClass("has-error");
        }
        var psw2_len2 = getLen('psw2');
        var psw2_value=$("#psw2").val();
        if (psw2_len2 !== 0) {
            psw2_null.hide();
            {
                if (psw2_value !==psw1_value) {
                    psw2_error.show();
                    psw2_f.show();
                    psw2_t.hide();
                    psw2_box.removeClass("has-success");
                    psw2_box.addClass("has-error");
                }
                if (psw2_value=== psw1_value) {
                    psw2_box.removeClass("has-error");
                    psw2_error.hide();
                    psw2_f.hide();
                    psw2_t.show();
                    psw2_box.addClass("has-success");
                }
            }
        }
    });

    //登录手机号验证(+)
    var phone2_box=$("#phone2_box");
    var phone2=$("#phone2");

    var phone2_null=$("#phone2_null");
    var phone2_error=$("#phone2_error");
    var phone2_f=$("#phone2_f");
    var phone2_t=$("#phone2_t");
    phone2.blur(function () {
        var phone_len1 = getLen('phone2');
        if (phone_len1== 0) {
            phone2_null.show();
            phone2_f.show();
            phone2_t.hide();
            phone2_error.hide();
            phone2_box.removeClass("has-success");
            phone2_box.addClass("has-error");

        }
        else{
            phone2_null.hide();

            $.ajax({
                type: "POST",
                url: "/checkLoginPhone",
                dataType: 'json',
                data: {
                    "login_phone": phone2.val()
                },
                success: function (data) {
                    //没找到该用户
                    if (parseInt(data) == 0) {
                        phone2_error.show();
                        phone2_f.show();
                        phone2_t.hide();
                        phone2_box.removeClass("has-success");
                        phone2_box.addClass("has-error");
                    }
                    //存在该用户
                    else if (data == 1) {
                        phone2_box.removeClass("has-error");
                        phone2_error.hide();
                        phone2_f.hide();
                        phone2_t.show();
                        phone2_box.addClass("has-success");
                    }
                    else{
                        console.log("数据异常，验证失败！");
                        phone2_error.show();
                        phone2_f.show();
                        phone2_t.hide();
                        phone2_box.removeClass("has-success");
                        phone2_box.addClass("has-error");
                    }
                },
                error:function() {
                    console.log("请求失败!");
                    phone2_error.show();
                    phone2_f.show();
                    phone2_t.hide();
                    phone2_box.removeClass("has-success");
                    phone2_box.addClass("has-error");
                }
            })
        }
    });
    //密码验证(+)
    var psw3_box=$("#psw3_box");
    var psw3_null=$("#psw3_null");
    var psw3_error=$("#psw3_error");
    var psw3_f=$("#psw3_f");
    var psw3_t=$("#psw3_t");
    var psw3=$("#psw3");
    psw3.blur(function(){
        var len = getLen('psw3');
        if (len == 0) {
            psw3_null.show();
            psw3_error.hide();
            psw3_f.show();
            psw3_t.hide();
            psw3_box.removeClass("has-success");
            psw3_box.addClass("has-error");
        }
        else{
            psw3_null.hide();
            {
                $.ajax({
                    type: "POST",
                    url: "/checkLoginPassword",
                    dataType: 'json',
                    data: {
                        "login_phone":$("#phone2").val(),
                        "login_password": $("#psw3").val()
                    },
                    success: function (data) {
                        //手机和密码不匹配
                        if (parseInt(data)== 0) {
                            psw3_error.show();
                            psw3_f.show();
                            psw3_t.hide();
                            psw3_box.removeClass("has-success");
                            psw3_box.addClass("has-error");
                        }
                        //手机和密码匹配
                        else if (parseInt(data) == 1) {
                            psw3_box.removeClass("has-error");
                            psw3_error.hide();
                            psw3_f.hide();
                            psw3_t.show();
                            psw3_box.addClass("has-success");
                        }
                        else{
                            console.log("数据异常！");
                            psw3_error.show();
                            psw3_f.show();
                            psw3_t.hide();
                            psw3_box.removeClass("has-success");
                            psw3_box.addClass("has-error");
                        }
                    },
                    error:function() {
                        console.log("请求失败！");
                        psw3_error.show();
                        psw3_f.show();
                        psw3_t.hide();
                        psw3_box.removeClass("has-success");
                        psw3_box.addClass("has-error");
                    }
                })
            }
        }
    });
    //生成图片验证码
    $(".fresh").on('click',function(){
        var img_src='/getKaptchaImage?t='+Math.random();
        $("#img").attr('src',img_src);

    });
    //图片验证码验证
    var img_box=$("#img_box");
    var img_code=$("#img_code");
    var img_f=$("#img_f");
    var img_t=$("#img_t");
    var img_null=$("#img_null");
    var img_error=$("#img_error");
    img_code.keyup(function () {
        //验证输入长度
        var len = getLen('img_code');
        var img_value=img_code.val();
        if (len == 0) {
            img_error.hide();
            img_t.hide();
            img_null.show();
            img_f.show();
            img_box.removeClass("has-error");
            img_box.addClass("has-error");
        }
        //验证验证码是否正确
        else{
            img_null.hide();
            {
                $.ajax({
                    type: "POST",
                    url: "/checkPicCode",
                    dataType: 'json',
                    data: {
                        "img_value": img_value
                    },
                    success: function (data) {
                        //验证码错误
                        if (parseInt(data) == 0) {
                            img_t.hide();
                            img_error.show();
                            img_f.show();
                            img_box.removeClass("has-error");
                            img_box.addClass("has-error");

                        }
                        //验证码正确
                        else if (parseInt(data) == 1) {
                            img_box.removeClass("has-error");
                            img_error.hide();
                            img_t.show();
                            img_f.hide();
                            img_box.addClass("has-success");
                        }
                        else{
                            console.log("返回数据错误！");
                            img_t.hide();
                            img_error.show();
                            img_f.show();
                            img_box.removeClass("has-error");
                            img_box.addClass("has-error");

                        }
                    },
                    error:function(){
                        console.log("请求失败！");
                        img_t.hide();
                        img_error.show();
                        img_f.show();
                        img_box.removeClass("has-error");
                        img_box.addClass("has-error");

                    }
                })
            }
        }
    });
    var login_information = new Array(3);
    for (var j = 0; j < 3; j++) {
    login_information[j] = $("#login_form").find('.form-group').eq(j);
    }
    var register_information = new Array(5);
    for (var i = 0; i < 5; i++) {
    register_information[i] = $("#register_form").find('.form-group').eq(i);
    }
    var loginBtn=$("#loginBtn");
    var registerBtn=$("#registerBtn");
    registerBtn.click(function(){
        for (var i = 0; i < 5; i++) {
            if (register_information[i].hasClass('has-error')) {
                break;
            }
            else if(phone1.val().length<2||msg_code.val().length<2||myName.val().length<2||psw1.val().length<2||psw2.val().length<2){
                break;
            }
        }
        if(i==5){
            $("#register_form").submit();
        }
        else{
            alert("请正确填写信息！");
        }
    });
    loginBtn.click(function(){
        for (var j = 0; j < 3; j++) {
            if (login_information[j].hasClass('has-error')) {
                break;
            }
            else if (phone2.val().length < 2 || psw3.val().length < 2 || img_code.val().length < 2) {
                break;
            }

        }
        if(j==3){
            $("#login_form").submit();
        }
        else{
            alert("请正确填写信息");
        }
    });





