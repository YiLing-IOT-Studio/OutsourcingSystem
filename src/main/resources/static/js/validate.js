/*update 2018.1.21
* content:修改语法错误*/
/*update 2018.1.23
* content:优化函数的引用，变量的声明和初始化，增减注释*/
/*update 2018.1.24
 * content:增加手机号验证：是否已经注册，请求后端 url: "/users"
 * 图片验证码点击后刷新前端部分，图片从后端获取。还需要后端改一改<a href="javascript:;" @click="changeCodeImg()">
 * 验证图片验证码是否正确，url: "/askimgcode",
 * 短信验证码点击后先后端请求数据并发送手机号，60秒后才能再次获取（后端还需要返回发送的验证码以便前端验证）url: "../Ajax/smsrandcodetest.ashx?phone=" + phone1.val()
 * 关于手机号和密码的请求的都请放在 url: "/users"*/

$(document).ready(function() {
    //隐藏错误提示和标志符号
    $('.help-block').hide();
    $(".form-control-feedback").hide();
    //获取输入框内容长度
    function getLen($id) {
        var myValue = $('#' + $id).val();
        return len = myValue.length;
    }
    //验证短信验证码
    function msg_validate(result) {
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
            var msg_value = msg_code.val();
            var msg_len2 = getLen('msg_code');
            if (msg_len2 !== 0) {
                msg_null.hide();
                {
                    if (msg_value !== result) {
                        msg_error.show();
                        msg_f.show();
                        msg_t.hide();
                        msg_box.removeClass("has-success");
                        msg_box.addClass("has-error");
                    }
                    else {
                        msg_box.removeClass("has-error");
                        msg_box.addClass("has-success");
                        msg_error.hide();
                        msg_f.hide();
                        msg_t.show();

                    }

                }
            }
        });
    }


    //获取关于手机号验证的相关元素
    var phone1 = $("#phone1");
    var phone1_f = $("#phone1_f");
    var phone1_t = $("#phone1_t");
    var phone1_error = $("#phone1_error");
    var phone1_null = $("#phone1_null");
    var phone1_box = $("#telephone1");
    var phone1_existed = $("#phone1-existed");
    //手机号码验证(+)
    phone1.keyup(function () {
        //获得手机号的长度，验证长度是否为空
        var phone1_len = getLen('phone1');
        if (phone1_len == 0) {
            phone1_error.hide();
            phone1_t.hide();
            phone1_f.show();
            phone1_null.show();
            phone1_box.remove("has-success");
            phone1_box.addClass("has-error");
        }
        //获得手机号的内容，验证值是否合法
        var phone1_value = phone1.val();
        var pattern;
        pattern = /^1[34578]\d{9}$/;
        if (phone1_len !== 0) {
            phone1_null.hide();

            {
                if (!pattern.test(phone1_value)) {
                    phone1_error.show();
                    phone1_f.show();
                    phone1_t.hide();
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
                            "phone1": phone1_value
                        },
                        success: function (data) {
                            //返回0，没有注册
                            if (parseInt(data) === 0) {
                                phone1_existed.show();
                                phone1_f.show();
                                phone1_box.removeClass("has-success");
                                phone1_box.addClass("has-error");
                                phone1_error.hide();
                                phone1_t.hide();
                            }
                            //返回1，注册成功
                            if (parseInt(data) === 1) {
                                phone1_f.hide();
                                phone1_error.hide();
                                phone1_t.show();
                                phone1_existed.hide();
                                phone1_box.removeClass("has-error");
                                phone1_box.addClass("has-success");
                            }
                        },
                        error:function(){
                            alert("请求失败！请稍后重试");
                        }
                    });
                }
            }
        }
    });
    //发送短信验证码
    var msg_btn = $("#msg_btn");
    // 定义发送时间间隔(s)
    var my_interval;
    my_interval = 60;
    var timeLeft = my_interval;
    /**
     * 绑定btn按钮的监听事件
     */
    var bindBtn = function () {
        msg_btn.click(function () {
            msg_btn.unbind('click');
            btn.attr('disabled', 'disabled');
            var phone1_box = $("#telephone1");
            if (phone1_box.hasClass("has-success")) {
                var pattern_msg=/\d{6}/;
                $.ajax({
                    type: "POST",
                    url: "/sendMsgCode",
                    dataType:'json',
                    data:{
                        "phone1" : $("#phone1").val()
                    },
                    success: function (result) {
                        //返回验证码的值（字符串）
                        if (pattern_msg.test(result)) {
                            alert("验证码已发送至您输入的手机号！有效期5分钟");
                            timeLeft=my_interval;
                            timeCount();
                            //短信验证码验证(+)
                            msg_validate(result);
                        }
                        else {
                            alert("验证码获取失败！请重新获取");
                            bindBtn();
                            msg_btn.remove('disabled');
                        }
                    },
                    error: function () {
                        alert("请求失败，请稍后重试！");
                    }
                });
            }
        });
    }
    //重新发送计时
    var timeCount = function() {
        window.setTimeout(function() {
            if(timeLeft > 0) {
                timeLeft -= 1;
                msg_btn.html(timeLeft + "后重新发送");
                timeCount();
            } else {
                msg_btn.html("重新发送");
                bindBtn();
            }
        }, 1000);
    }

    //昵称验证
    //获取有关昵称的元素
    //昵称验证
    var name=$("#name");
    var name_box=$("#name_box");
    var name_f=$("#name_f");
    var name_t=$("#name_t");
    var name_error=$("#name_error");
    var name_null=$("#name_null");
    name.keyup(function () {
            var name_len1 = getLen('name');
            if (name_len1 == 0) {
                name_error.hide();
                name_t.hide();
                name_f.show();
                name_null.show();
                name_box.remove("has-success");
                name_box.addClass("has-error");
            }
            var name_value = name.val();
            var name_len2 = getLen('name');
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


    psw1.keyup(function () {
            var psw1_len1 = getLen('psw1');
            if (psw1_len1 == 0) {
                psw1_error.hide();
                psw1_null.show();
                psw1_t.hide();
                psw1_f.show();
                psw1_box.removeClass("has-success");
                psw1_box.addClass("has-error");
            }
            var psw1_value =psw1.val();
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
    psw2.keyup(function () {
            var psw2_len1 = getLen('psw2');
            if (psw2_len1 == 0) {
                psw2_error.hide();
                psw2_f.show();
                psw2_t.hide();
                psw2_null.show();
                psw2_box.removeClass("has-success");
                psw2_box.addClass("has-error");
            }
            var psw2_value = psw2.val();
            var psw1_value = $("#psw1").val();
            var psw2_len2 = getLen('psw2');
            if (psw2_len2 !== 0) {
                psw2_null.hide();
                {
                    if (psw2_value !== psw1_value) {
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
    //图片验证码验证
    $("#image_code").focus(function () {


        $("#image_code").keyup(function () {
            //验证输入长度
            var len = getLen('image_code');
            if (len == 0) {
                $("#code1_error").hide();
                $("#code1_t").hide();
                $("#code1_null").show();
                $("#code1_f").show();
                $("#img_code").removeClass("has-error");
                $("#img_code").addClass("has-error");
            }
            //验证验证码是否正确
            var value = $("#image_code").val();

            var len2 = getLen('image_code');
            if (len2 !== 0) {
                $("#code1_null").hide();
                $("#code1_f").hide();
                {
                    $.ajax({
                        type: "POST",
                        url: "/checkPicCode",
                        dataType: 'json',
                        data: {
                            "image_code": value
                        },
                        success: function (data) {
                            if (data == 0) {
                                $("#code1_t").hide();
                                $("#code1_error").show();
                                $("#code1_f").show();
                                $("#img_code").removeClass("has-success");
                                $("#img_code").addClass("has-error");
                            }
                            if (data == 1) {
                                $("#img_code").removeClass("has-error");
                                $("#code1_error").hide();
                                $("#code1_t").show();
                                $("#code1_f").hide();
                                $("#img_code").addClass("has-success");
                            }
                        }
                    })
                }
            }
        });
    });

    //账号用户名验证(+)


        $("#account").keyup(function () {
            var len = getLen('account');
            if (len == 0) {
                $("#account_null").show();
                $("#account_f").show();
                $("#account_t").show();
                $("#account_error").hide();
                $("#account_login").removeClass("has-success");
                $("#account_login").addClass("has-error");
            }
            var len2 = getLen('account');
            if (len2 !== 0) {
                $("#account_null").hide();
                {
                    //未找到该用户
                    $.ajax({
                        type: "POST",
                        url: "/checkLoginPhone",
                        dataType: 'json',
                        data: {
                            "phone2": $("#account").val()
                        },
                        success: function (data) {
                            if (data == 0) {
                                $("#account_error").show();
                                $("#account_f").show();
                                $("#account_t").hide();
                                $("#account_login").removeClass("has-success");
                                $("#account_login").addClass("has-error");
                            }
                            if (data == 1) {
                                $("#account_login").removeClass("has-error");
                                $("#account_error").hide();
                                $("#account_f").hide();
                                $("#account_t").show();
                                $("#account_login").addClass("has-success");
                            }
                        }
                    })
                }
            }
        });

    //密码验证(+)

        $("#password3").keyup(function () {
            var len = getLen('password3');
            if (len == 0) {
                $("#psw3_null").show();
                $("#psw3_error").hide();
                $("#psw3_f").show();
                $("#psw3_t").hide();
                $("#password_login").removeClass("has-success");
                $("#password_login").addClass("has-error");
            }
            var len2 = getLen('password3');
            if (len2 !== 0) {
                $("#psw3_null").hide();
                {
                    //密码不匹配！
                    $.ajax({
                        type: "POST",
                        url: "/checkLoginPassword",
                        dataType: 'json',
                        data: {
                            "password3": $("#password3").val()
                        },
                        success: function (data) {
                            if (data == 0) {
                                $("#psw3_error").show();
                                $("#psw3_f").show();
                                $("#psw3_t").hide();
                                $("#password_login").removeClass("has-success");
                                $("#password_login").addClass("has-error");
                            }
                            if (data == 1) {
                                $("#password_login").removeClass("has-error");
                                $("#code2_error").hide();
                                $("#psw3_f").hide();
                                $("#psw3_t").show();
                                $("#password_login").addClass("has-success");
                            }
                        }
                    })
                }
            }
        });

    /*update 2018.1.21
     *author:yyq
     * content:优化注册和登录的按钮，仅当用户所有信息填写正确时可以使用，否则禁用按钮，不能点击
     *.btn1:注册按钮
     *.btn2:登录按钮*/
     $('.form-group :input').bind('input propertychange', function () {
         //注册按钮
    var register = $("#btn1");
    var i;
    var register_information = new Array(5);
    for (i = 0; i < 5; i++) {
        register_information[i] = $("#register_form").find('.form-group').eq(i);
    }
    for (i = 0; i < 5; i++) {
        var flag = register_information[i].hasClass('has-error');
        if (flag == true) {
            break;
        }
    }
    if (i == 5) {
        register.attr('disabled', false);
    }
    else {
        register.attr('disabled', true);
    }
    //登录按钮
         var login = $("#btn2");
         var j;
         var login_information = new Array(3);
         for (j = 0; j < 3; j++) {
             register_information[i] = $("#register_form").find('.form-group').eq(i);
         }
         for (j = 0; j < 3; j++) {
             var flag2 = login_information[i].hasClass('has-error');
             if (flag2 == true) {
                 break;
             }
         }
         if (i == 3) {
             login .attr('disabled', false);
         }
         else {
             login .attr('disabled', true);
         }
});
});


