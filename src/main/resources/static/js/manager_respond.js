(function() {
    //查看外包信息
    function getMyDate(str) {
        var oDate = new Date(str),
            oYear = oDate.getFullYear(),
            oMonth = oDate.getMonth() + 1,
            oDay = oDate.getDate(),
            oHour = oDate.getHours(),
            oMin = oDate.getMinutes(),
            oSen = oDate.getSeconds(),
            oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay) + ' ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen); //最后拼接时间
        return oTime;
    };
    //补0操作
    function getzf(num) {
        if (parseInt(num) < 10) {
            num = '0' + num;
        }
        return num;
    }

    $.ajaxSetup( {
        beforeSend: function(xhr) {
            xhr.setRequestHeader('X-CSRF-TOKEN',$("meta[name='_csrf']").attr("content"));
        },
        global:true
    } );
    allMsg();

    function allMsg() {
        $.post('/manager/getAllOutsourcing',{}, function(data) {
            var moreMsg = $('.moreMsg');
            moreMsg.empty();
            $('.table').removeClass('hidden');
            moreMsg.addClass('hidden');
            $('#title').html('外包信息登记表');
            $('#tBody').empty();
            for (i in data) {
                // alert('a');
                var row = $('<tr>' +
                    '<td>' + data[i].name + '</td>' +
                    '<td>' + data[i].publisher + '</td>' +
                    '<td>' + data[i].state + '</td>' +
                    '<td>' + data[i].progress + '%</td>' +
                    '<td>' + data[i].publishTime + '</td>' +
                    '<td>' + getMyDate(data[i].registrationDeadline*1000) + '</td>' +
                    '<td>' + getMyDate(data[i].projectDeadline*1000) + '</td>' +
                    '<td><a class="contractor" data-index="' + data[i].id + '" href="#">查看所有接包人/编辑</a></td>' +
                    '<td><a class="more" data-index="' + data[i].id + '" href="#">查看更多信息/编辑</a></td>' +
                    '</tr>');
                $('#tBody').append(row);
            }
            //查看接包人/编辑
            $('.contractor').click(function() {
                var thisHead = $(this).parent().siblings().eq(0).html();
                conPost(thisHead);

                function conPost(con) {
                    $.post('/manager/getAllUserInfo', {
                        name: con
                    }, function contractor(data) {
                        var moreMsg = $('.moreMsg');
                        moreMsg.empty();
                        $('.table').addClass('hidden');
                        moreMsg.removeClass('hidden');
                        $('#title').html('接包人信息/编辑');
                        var conHead = $('<span id="back" class="glyphicon glyphicon-arrow-left"></span>' +
                            '<form id="form" class="col-sm-8 form col-center-block" action="">' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-6">' +
                            '<i class="fa fa-telegram"></i>' +
                            '<label>外包名：</label>' +
                            '<span>' + data[0].name + '</span>' +
                            '</div>' +
                            '<div class="col-sm-6">' +
                            '<i class="fa fa-user-o publisher"></i>' +
                            '<label>发包者：</label>' +
                            '<span>' + data[0].publisher + '</span>' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-12">' +
                            '<i class="fa fa-user-o publisher"></i>' +
                            '<label>接包者：</label>' +
                            '</div>' +
                            '</div>' +
                            '</form>');
                        moreMsg.append(conHead);
                        if (data[0].contractor.length == 0) {
                            var oneCon = $('<div class="col-sm-12">' +
                                '<div class="alert alert-warning col-sm-12" role="alert">' +
                                '<span>暂无人接包</span>' +
                                '</div>' +
                                '</div>');
                            $('.form').append(oneCon);
                        } else {
                            for (i in data.contractor) {
                                var sex, promise, contact;
                                if (data[0].contractor[i].gender == 'lady') {
                                    sex = '女';
                                } else {
                                    sex = '男';
                                }
                                if (data[0].contractor[i].promise == 'true') {
                                    promise = 'ok';
                                } else {
                                    promise = 'remove';
                                }
                                if (data[0].contractor[i].contract == 'true') {
                                    contact = 'ok';
                                } else {
                                    contact = 'remove';
                                }
                                var oneCon = $('<div class="col-sm-12">' +
                                    '<div class="alert alert-warning col-sm-12" role="alert">' +
                                    '<div class="col-sm-12">' +
                                    '<label>姓名：</label>' +
                                    '<span>' + data[0].contractor[i].name + '</span>' +
                                    '</div>' +
                                    '<div class="col-sm-12">' +
                                    '<label>性别：</label>' +
                                    '<span>' + sex + '</span>' +
                                    '</div>' +
                                    '<div class="col-sm-12">' +
                                    '<label>电话：</label>' +
                                    '<span>' + data[0].contractor[i].phone + '</span>' +
                                    '</div>' +
                                    '<div class="col-sm-12">' +
                                    '<label>负责项目：</label>' +
                                    '<span>' + data[0].contractor[i].project + '</span>' +
                                    '</div>' +
                                    '<div class="col-sm-12">' +
                                    '<label>是否签订保密协议：</label>' +
                                    '<span class="glyphicon glyphicon-' + promise + '"></span>' +
                                    '</div>' +
                                    '<div class="col-sm-12">' +
                                    '<label>合同签订情况：</label>' +
                                    '<span class="glyphicon glyphicon-' + contact + '"></span>' +
                                    '</div>' +
                                    '<button type="button" class="btn btn-danger pull-right conBtn" data-id="' + data[0].contractor[i].id + '">编辑</button>' +
                                    '</div>');
                                $('.form').append(oneCon);
                            }
                        }
                        //后退
                        $('#back').click(function() {
                            allMsg();
                        });
                        $('.conBtn').click(function() {
                            $('#back').remove();
                            $('.form').empty();
                            var id = $(this).attr('data-id');
                            for (var i = 0; i < data[0].contractor[i].length; i++) {
                                if (data.contractor[i].id == id) {

                                }
                            }
                            var sex, promise, contact;
                            if (data[0].contractor[id].gender == 'lady') {
                                sex = '女';
                            } else {
                                sex = '男';
                            }
                            if (data[0].contractor[id].promise == 'true') {
                                promise = 'ok';
                            } else {
                                promise = 'remove';
                            }
                            if (data[0].contractor[id].contract == 'true') {
                                contact = 'ok';
                            } else {
                                contact = 'remove';
                            }
                            var editMsg = $('<div class="col-sm-12">' +
                                '<div class="alert alert-warning col-sm-12" role="alert">' +
                                '<form class="col-sm-12">' +
                                '<div class="col-sm-12">' +
                                '<div class="col-sm-6 form-group">' +
                                '<label for="name">姓名：</label>' +
                                '<input type="text" class="form-control" id="name" name="name" value="' + data[0].contractor[id].name + '">' +
                                '</div>' +
                                '<div class="col-sm-6 form-group">' +
                                '<label for="gender">性别：</label>' +
                                '<input type="text" class="form-control" id="gender" name="gender" value="' + sex + '">' +
                                '</div>' +
                                '</div>' +
                                '<div class="col-sm-12">' +
                                '<div class="col-sm-12 form-group">' +
                                '<label for="phone">电话：</label>' +
                                '<input type="text" class="form-control" id="phone" name="phone" value="' + data[0].contractor[id].phone + '">' +
                                '</div>' +
                                '</div>' +
                                '<div class="col-sm-12">' +
                                '<div class="col-sm-12 form-group">' +
                                '<label for="project">负责项目：</label>' +
                                '<input type="text" class="form-control" id="project" name="project" value="' + data[0].contractor[id].project + '">' +
                                '</div>' +
                                '</div>' +
                                '<div class="col-sm-12">' +
                                '<div class="col-sm-6">' +
                                '<label>是否签订保密协议：</label>' +
                                '<span class="glyphicon glyphicon-' + promise + '"></span>' +
                                '</div>' +
                                '<div class="col-sm-6">' +
                                '<label>合同签订情况：</label>' +
                                '<span class="glyphicon glyphicon-' + contact + '"></span>' +
                                '</div>' +
                                '</div>' +
                                '<button type="button" class="btn btn-danger pull-right sub" data-name="' + data.name + '" data-id="' + data[0].contractor[id].id + '">提交</button>' +
                                '<button type="button" class="btn btn-primary pull-right back">返回</button>' +
                                '</form>' +
                                '</div>' +
                                '</div>');
                            $('.form').append(editMsg);
                            $('.back').click(function() {
                                conPost(thisHead);
                            });
                            $('.sub').click(function() {
                                var _this = $(this);
                                // conPost(thisHead);
                                $.post('http://localhost:7070/post', {
                                    data: $('#form').serialize(),
                                    name: _this.attr('data-name')
                                }, function(data) {
                                    conPost(thisHead);
                                })
                            })
                        });
                    })
                }
            });
            //查看更多信息/编辑
            $('.more').click(function() {
                var th = $(this).attr('data-index');
                morePost(th);

                function morePost(th) {
                    $.post('/manager/getOneOutsourcingById', {
                        data: th
                    }, function(data) {
                        var moreMsg = $('.moreMsg');
                        moreMsg.empty();
                        $('.table').addClass('hidden');
                        moreMsg.removeClass('hidden');
                        $('#title').html('更多信息/编辑');
                        var msg = $('<form class="col-sm-8 form col-center-block" action="">' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-6">' +
                            '<i class="fa fa-telegram"></i>' +
                            '<label>外包名：</label>' +
                            '<span>' + data[0].name + '</span>' +
                            '</div>' +
                            '<div class="col-sm-6">' +
                            '<i class="fa fa-user-o publisher"></i>' +
                            '<label>发包者：</label>' +
                            '<span>' + data[0].publisher + '</span>' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-6">' +
                            '<i class="fa fa-lightbulb-o"></i>' +
                            '<label>项目发布时间：</label>' +
                            '<span>' + data[0].publishTime + '</span>' +
                            '</div>' +
                            '<div class="col-sm-6">' +
                            '<i class="fa fa-shield"></i>' +
                            '<label>项目安全等级：</label>' +
                            '<span class="rank"></span>' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-6">' +
                            '<i class="fa fa-tag"></i>' +
                            '<label>项目分类：</label>' +
                            '<span>' + data[0].category + '</span>' +
                            '</div>' +
                            '<div class="col-sm-6">' +
                            '<i class="fa fa-spinner"></i>' +
                            '<label>项目状态：</label>' +
                            '<span>' + data[0].state + '</span>' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-12">' +
                            '<i class="fa fa-rmb"></i>' +
                            '<label>项目金额：</label>' +
                            '<span>' + data[0].amount + '</span>' +
                            '</div>' +
                            '</div>' +
                            '<div class="content col-sm-12">' +
                            '<div class="col-sm-12">' +
                            '<i class="fa fa-question-circle"></i>' +
                            '<label>项目说明：</label>' +
                            '<span>' + data[0].content + '</span>' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-12">' +
                            '<i class="fa fa-pencil-square"></i>' +
                            '<label>项目要求：</label>' +
                            '<span>' + data[0].requirement + '</span>' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-12">' +
                            '<i class="fa fa-history"></i>' +
                            '<label>项目进度：</label>' +
                            '<div class="progress">' +
                            '<div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="' + data[0].progress + '" aria-valuemin="0" aria-valuemax="100" style="width: ' + data[0].progress + '%">' +
                            '<span class="sr-only">' + data[0].progress + '% Complete (success)</span>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-12">' +
                            '<i class="fa fa-hourglass-start"></i>' +
                            '<label>报名截止时间：</label>' +
                            '<span>' + data[0].registrationDeadline + '</span>' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-12">' +
                            '<i class="fa fa-hourglass-end"></i>' +
                            '<label>项目截止时间：</label>' +
                            '<span>' + data[0].projectDeadline + '</span>' +
                            '</div>' +
                            '</div>' +
                            '<div class="btnBox col-sm-3 pull-right">' +
                            '<button type="button" class="btn btn-primary return">返回</button>' +
                            '<button type="button" class="btn btn-danger edit">编辑</button>' +
                            '</div>' +
                            '</form>');
                        moreMsg.append(msg);
                        var rank = $('.rank');
                        for (var i = 0; i < data[0].rank; i++) {
                            rank.append('<i class="fa fa-star"></i>');
                        }
                        //返回键
                        $('.return').click(function() {
                                $('.table').removeClass('hidden');
                                moreMsg.addClass('hidden');
                            })
                            //编辑键
                        $('.edit').click(function() {
                            moreMsg.empty();
                            var msg = $('<form id="form" class="col-sm-8 form col-center-block" action="" method="post">' +
                                '<div class="col-sm-12">' +
                                '<div class="col-sm-6">' +
                                '<i class="fa fa-telegram"></i>' +
                                '<label for="name">外包名：</label>' +
                                '<input class="form-control" id="name" name="name" value="' + data[0].name + '">' +
                                '</div>' +
                                '<div class="col-sm-6">' +
                                '<i class="fa fa-user-o publisher"></i>' +
                                '<label for="publisher">发包者：</label>' +
                                '<input class="form-control" id="publisher" name="publisher" value="' + data[0].publisher + '">' +
                                '</div>' +
                                '</div>' +
                                '<div class="col-sm-12">' +
                                '<div class="col-sm-6">' +
                                '<i class="fa fa-lightbulb-o"></i>' +
                                '<label for="publishTime">项目发布时间：</label>' +
                                '<input class="form-control" id="publishTime" name="publishTime" value="' + data[0].publishTime + '">' +
                                '</div>' +
                                '<div class="col-sm-6">' +
                                '<i class="fa fa-shield"></i>' +
                                '<label for="rank">项目安全等级：</label>' +
                                '<input class="form-control" id="rank" name="rank" value="' + data[0].rank + '">' +
                                '</div>' +
                                '</div>' +
                                '<div class="col-sm-12">' +
                                '<div class="col-sm-6">' +
                                '<i class="fa fa-tag"></i>' +
                                '<label for="category">项目分类：</label>' +
                                '<input class="form-control" id="category" name="category" value="' + data[0].category + '">' +
                                '</div>' +
                                '<div class="col-sm-6">' +
                                '<i class="fa fa-spinner"></i>' +
                                '<label for="state">项目状态：</label>' +
                                '<input class="form-control" id="state" name="state" value="' + data[0].state + '">' +
                                '</div>' +
                                '</div>' +
                                '<div class="col-sm-12">' +
                                '<div class="col-sm-12">' +
                                '<i class="fa fa-rmb"></i>' +
                                '<label for="amount">项目金额：</label>' +
                                '<input class="form-control" id="amount" name="amount" value="' + data[0].amount + '">' +
                                '</div>' +
                                '</div>' +
                                '<div class="content col-sm-12">' +
                                '<div class="col-sm-12">' +
                                '<i class="fa fa-question-circle"></i>' +
                                '<label for="content">项目说明：</label>' +
                                '<input class="form-control" id="content" name="content" value="' + data[0].content + '">' +
                                '</div>' +
                                '</div>' +
                                '<div class="col-sm-12">' +
                                '<div class="col-sm-12">' +
                                '<i class="fa fa-pencil-square"></i>' +
                                '<label for="requirement">项目要求：</label>' +
                                '<input class="form-control" id="requirement" name="requirement" value="' + data[0].requirement + '">' +
                                '</div>' +
                                '</div>' +
                                '<div class="col-sm-12">' +
                                '<div class="col-sm-12">' +
                                '<i class="fa fa-history"></i>' +
                                '<label for="progress">项目进度：</label>' +
                                '<input class="form-control" id="progress" name="progress" value="' + data[0].progress + '">' +
                                '</div>' +
                                '</div>' +
                                '<div class="col-sm-12">' +
                                '<div class="col-sm-12">' +
                                '<i class="fa fa-hourglass-start"></i>' +
                                '<label for="registrationDeadline">报名截止时间：</label>' +
                                '<input class="form-control" id="registrationDeadline" name="registrationDeadline" value="' + getMyDate(data[0].registrationDeadline*1000) + '">' +
                                '</div>' +
                                '</div>' +
                                '<div class="col-sm-12">' +
                                '<div class="col-sm-12">' +
                                '<i class="fa fa-hourglass-end"></i>' +
                                '<label for="projectDeadline">项目截止时间：</label>' +
                                '<input class="form-control" id="projectDeadline" name="projectDeadline"  value="' + getMyDate(data[0].projectDeadline*1000) + '">' +
                                '</div>' +
                                '<div class="col-sm-12 btn-box">' +
                                '<div class="col-sm-12">' +
                                '<button type="button" class="btn btn-danger pull-right" data-id="' + data[0].id + '">提交</button>' +
                                '<button type="button" class="btn btn-primary pull-right back">返回</button>' +
                                '</div>' +
                                '</div>' +
                                '</form>');
                            moreMsg.append(msg);
                            $('.back').click(function() {
                                morePost(th);
                            })
                            $('.btn-block').click(function() {
                                var _this = $(this);
                                $.post('http://localhost:7070/post', {
                                    data: $('#form').serialize(),
                                    name: _this.attr('data-id')
                                }, function() {
                                    morePost(th);
                                })
                            })
                        })
                    });
                }
            })

        })
    }
    //申请批复
    // apply();
    //
    // function apply() {
    //     $.get('', function(data) {
    //         // data = JSON.parse(data);
    //         $(".allMsg").empty();
    //         for (i in data) {
    //             var respondEmail = $('<div class="one">' +
    //                 '<div class="alert alert-danger clearfix" role="alert">' +
    //                 '<span>' +
    //                 '<button type="button" class="btn btn-default applicant" data-name="' + data[i].applicant + '">' + data[i].applicant + '</button>' +
    //                 '申请成为发包人</span>' +
    //                 '<div class="pull-right checkBox" data-index="' + data[i].id + '">' +
    //                 '</div>' +
    //                 '</div>' +
    //                 '</div>');
    //             $(".allMsg").append(respondEmail);
    //             if (data[i].status) {
    //                 $('.checkBox').eq(i).html('<button class="btn btn-danger">' + data[i].status + '</button>');
    //             } else {
    //                 $('.checkBox').eq(i).html('<button class="btn btn-primary agree">同意</button>' +
    //                     '<button class="btn btn-danger disagree">拒绝</button>');
    //             }
    //         }
    //         $('.applicant').click(function() {
    //             var _this = $(this);
    //             $.post('', {
    //                 name: _this.attr('data-name')
    //             }, function(data) {
    //
    //             })
    //         })
    //
    //         function check($name, url, result) {
    //             $("." + $name).click(function() {
    //                 var _this = $(this).parent();
    //                 $.post(url, {
    //                     id: _this.attr("data-index"),
    //                     result: result
    //                 }, function() {
    //                     respond();
    //                 })
    //             });
    //         }
    //         check('agree', '', 'true');
    //         check('disagree', '', 'false');
    //     });
    // }
})()