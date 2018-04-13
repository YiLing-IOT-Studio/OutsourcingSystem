(function() {
    //查看外包信息
    function allMsg() {
        $.get('', function(data) {
            $('#tBody').empty();
            for (i in data) {
                var row = $('<tr>' +
                    '<td>' + data[i].name + '</td>' +
                    '<td>' + data[i].publisher + '</td>' +
                    '<td>' + data[i].state + '</td>' +
                    '<td>' + data[i].progress + '%</td>' +
                    '<td>' + data[i].publishTime + '</td>' +
                    '<td>' + data[i].registrationDeadline + '</td>' +
                    '<td>' + data[i].projectDeadline + '</td>' +
                    '<td><a class="more" data-index="' + data[i].id + '" href="#">查看更多信息/编辑</a></td>' +
                    '</tr>');
                $('#tBody').append(row);
            }
            $('.more').click(function() {
                var _this = $(this);
                $.post('', {
                    data: _this.attr('data-index')
                }, function(data) {
                    var moreMsg = $('.moreMsg');
                    moreMsg.empty();
                    $('.table').addClass('hidden');
                    moreMsg.removeClass('hidden');
                    $('.title').html('更多信息/编辑');
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
                        '<button class="btn btn-primary return">返回</button>' +
                        '<button class="btn btn-danger edit">编辑</button>' +
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
                        var msg = $('<form class="col-sm-8 form col-center-block" action="" method="post">' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-6">' +
                            '<i class="fa fa-telegram"></i>' +
                            '<label for="name">外包名：</label>' +
                            '<input type="email" class="form-control" id="name" name="name" value="' + data[0].name + '">' +
                            '</div>' +
                            '<div class="col-sm-6">' +
                            '<i class="fa fa-user-o publisher"></i>' +
                            '<label for="publisher">发包者：</label>' +
                            '<input type="email" class="form-control" id="publisher" name="publisher" value="' + data[0].publisher + '">' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-6">' +
                            '<i class="fa fa-lightbulb-o"></i>' +
                            '<label for="publishTime">项目发布时间：</label>' +
                            '<input type="email" class="form-control" id="publishTime" name="publishTime" value="' + data[0].publishTime + '">' +
                            '</div>' +
                            '<div class="col-sm-6">' +
                            '<i class="fa fa-shield"></i>' +
                            '<label for="rank">项目安全等级：</label>' +
                            '<input type="email" class="form-control" id="rank" name="rank" value="' + data[0].rank + '">' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-6">' +
                            '<i class="fa fa-tag"></i>' +
                            '<label for="category">项目分类：</label>' +
                            '<input type="email" class="form-control" id="category" name="category" value="' + data[0].category + '">' +
                            '</div>' +
                            '<div class="col-sm-6">' +
                            '<i class="fa fa-spinner"></i>' +
                            '<label for="state">项目状态：</label>' +
                            '<input type="email" class="form-control" id="state" name="state" value="' + data[0].state + '">' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-12">' +
                            '<i class="fa fa-rmb"></i>' +
                            '<label for="amount">项目金额：</label>' +
                            '<input type="email" class="form-control" id="amount" name="amount" value="' + data[0].amount + '">' +
                            '</div>' +
                            '</div>' +
                            '<div class="content col-sm-12">' +
                            '<div class="col-sm-12">' +
                            '<i class="fa fa-question-circle"></i>' +
                            '<label for="content">项目说明：</label>' +
                            '<input type="email" class="form-control" id="content" name="content" value="' + data[0].content + '">' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-12">' +
                            '<i class="fa fa-pencil-square"></i>' +
                            '<label for="requirement">项目要求：</label>' +
                            '<input type="email" class="form-control" id="requirement" name="requirement" value="' + data[0].requirement + '">' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-12">' +
                            '<i class="fa fa-history"></i>' +
                            '<label for="progress">项目进度：</label>' +
                            '<input type="email" class="form-control" id="progress" name="progress" value="' + data[0].progress + '">' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-12">' +
                            '<i class="fa fa-hourglass-start"></i>' +
                            '<label for="registrationDeadline">报名截止时间：</label>' +
                            '<input type="email" class="form-control" id="registrationDeadline" name="registrationDeadline" value="' + data[0].registrationDeadline + '">' +
                            '</div>' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-12">' +
                            '<i class="fa fa-hourglass-end"></i>' +
                            '<label for="projectDeadline">项目截止时间：</label>' +
                            '<input type="email" class="form-control" id="projectDeadline" name="projectDeadline"> value="' + data[0].projectDeadline + '"' +
                            '</div>' +
                            '<div class="col-sm-12">' +
                            '<div class="col-sm-12">' +
                            '<button type="submit" class="btn btn-primary btn-block">提交</button>' +
                            '</div>' +
                            '</div>' +
                            '</form>');
                        moreMsg.append(msg);
                    })
                });
            })

        })
    }
    //申请批复
    apply();

    function apply() {
        $.get('', function(data) {
            // data = JSON.parse(data);
            $(".allMsg").empty();
            for (i in data) {
                var respondEmail = $('<div class="one">' +
                    '<div class="alert alert-danger clearfix" role="alert">' +
                    '<span>' +
                    '<button type="button" class="btn btn-default applicant" data-toggle="tooltip" data-placement="bottom" title="点击可查看该人信息">' + data[i].applicant + '</button>' +
                    '申请成为发包人</span>' +
                    '<div class="pull-right checkBox" data-index="' + data[i].id + '">' +
                    '</div>' +
                    '</div>' +
                    '</div>');
                $(".allMsg").append(respondEmail);
                if (data[i].status) {
                    $('.checkBox').eq(i).html('<button class="btn btn-danger">' + data[i].status + '</button>');
                } else {
                    $('.checkBox').eq(i).html('<button class="btn btn-primary agree">同意</button>' +
                        '<button class="btn btn-danger disagree">拒绝</button>');
                }
            }

            function check($name, url, result) {
                $("." + $name).click(function() {
                    var _this = $(this).parent();
                    $.post(url, {
                        id: _this.attr("data-index"),
                        result: result
                    }, function() {
                        respond();
                    })
                });
            }
            check('agree', '', 'true');
            check('disagree', '', 'false');
        });
    }
})()