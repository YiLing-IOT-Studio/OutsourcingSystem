/**
 * Created by yyq on 2018/3/1.
 */
//变量声明
var put_word=$("#put_word");
var mySearch=$("#search");
var amountSort=$("#amount_sort");
var timeSort=$("#time_sort");
var clear='';
//把令牌值先直接保存在cookie里,以待传给manager页面
var token=$("meta[name='_csrf']").attr("content");
$.cookie('token',token);
$(function(){
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(event,xhr,options) {
        xhr.setRequestHeader(header, token);
    });
    // ajaxTest(1);
})
//填充数据类
function putIn(data){
    put_word.html(clear);
    $.each(data, function (index, obj) {
        if (index != (data.length)) {
            var sec = $('<section class="row"></section>');
            // 添加标题
            var title = $('<p class="h4"></p>');
            title.append('<span class="badge">'+obj['state']+'</span>'+'<strong>' + obj['name'] + '</strong>');
            sec.append(title);

            //标签
            var tags=$('<span class="label label-default">'+obj['category']+'</span>');
            sec.append(tags);

            // 主体
            var SelfIntro = $('<div></div>');
            SelfIntro.append(obj['content'] + '<br/>');
            sec.append(SelfIntro);

            //金额
            var Umoney = $('<p class="text-right"></p>');
            Umoney.append('<span class="money_text">'  + obj['amount']+ '元' + '</span>');
            sec.append(Umoney);

            //其他信息
            var Details=$('<p></p>');
            Details.append(obj['publisher']+'&nbsp;&nbsp;&nbsp;&nbsp;'+obj['publishTime']+'&nbsp;&nbsp;&nbsp;&nbsp;'+obj['requirement']);
            sec.append(Details);

            //进度条
            var fix_string=obj['progress']+'%';
            var progress=$('<div class="progress"></div>');
            progress.append('<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width:2em;width:'+fix_string+';">'+fix_string+'</div>');
            sec.append(progress);
            //报名申请
            var oBtn=$("<div class='text-right'></div>");
            oBtn.append("<button class='btn btn-primary apply' value='申请加入'>申请加入</button>");
            sec.append(oBtn);

            put_word.append(sec);
        }
    });


}

//页面请求类
function ajaxTest(currentPage) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(event, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    //加载时请求
    $.ajax({
        type: 'POST',
        url: '/getAllOutsourcingInfo/fillMessage',
        dataType: 'json',
        data: {
            rows:"10",
            pageNo:currentPage
        },
        success: function (data) {
            var rows=10;
            //放入数据
            putIn(data);
            scrollTo(0,0);//回到顶部
            //分页
            $("#pagination").paging({
                rows:rows,//每页显示条数
                pageNo:currentPage,//当前所在页码
                totalPage:data[data.length-1]['totalPage'],//总页数
                totalSize:data[data.length-1]['totalSize'],//总记录数
                callback:function(currentPage){
                    ajaxTest(currentPage);
                }
            });
            // $(".apply").click(function(){
            //     var oName=$(this).parent().parent().find('strong').text();
            //     var uName=$("#username").text();
            //     $.ajax({
            //         type: 'POST',
            //         url: '/',
            //         dataType: 'json',
            //         data: {
            //             "oName":oName,
            //             "uName":uName
            //         },
            //         success:function(data){
            //             alert(data);
            //         },
            //         error:function(){
            //             alert("请求失败");
            //         }
            //     });
            // })

        },
        error: function () {
            alert("获取消息失败");
        }
    });
}
ajaxTest(1);

//搜索类
function searchInfo(currentPage){
    //获取搜索关键字
    var keyWord=mySearch.val();
    $.ajax({
            type:'POST',
            url:'/getAllOutsourcingInfo/search',
            dataType:'json',
            data:{
                rows:"10",
                pageNo:currentPage,
                'searchWord':keyWord
            },
            success: function (data) {
                var rows=10;
                //放入数据
                putIn(data);
                scrollTo(0,0);//回到顶部
                //分页
                $("#pagination").paging({
                    rows:rows,//每页显示条数
                    pageNo:currentPage,//当前所在页码
                    totalPage:data[data.length-1]['totalPage'],//总页数
                    totalSize:data[data.length-1]['totalSize'],//总记录数
                    callback:function(currentPage){
                        searchInfo(currentPage);
                    }
                })
                // $(".apply").click(function(){
                //     var oName=$(this).parent().parent().find('strong').text();
                //     var uName=$("#username").text();
                //     $.ajax({
                //         type: 'POST',
                //         url: '/',
                //         dataType: 'json',
                //         data: {
                //             "oName":oName,
                //             "uName":uName
                //         },
                //         success:function(data){
                //             alert(data);
                //         },
                //         error:function(){
                //             alert("请求失败");
                //         }
                //     });
                // })
            },
            error: function () {
                alert("获取消息失败");
            }

        })
}

//提交表单类
function submitInfo(currentPage){
        //项目分类
        var checkboxes=$("input[name='_category']");
        var myCategories='';
        for(var i=0;i<checkboxes.length;i++){
            if(checkboxes[i].checked==true){
                myCategories+=checkboxes[i].value+',';
            }
        }
        //项目状态
        var stateRadios=$("input[name='_state']");
        var myState='';
        for(var j=0;j<stateRadios.length;j++){
            if(stateRadios[j].checked==true){
                myState=stateRadios[j].value;
            }
        }
        //项目金额
        var amountRadios=$("input[name='_amount']");
        var myAmount='';
        for(var k=0;k<amountRadios.length;k++){
            if(amountRadios[k].checked==true){
                myAmount=amountRadios[k].value;
            }
        }


        $.ajax({
            type:'POST',
            url:'/getAllOutsourcingInfo/classifySearch',
            dataType:'json',
            data:{
                rows:"10",
                pageNo:currentPage,
                "myCategories":myCategories,
                "myState":myState,
                "myAmount":myAmount,

            },
            success: function (data) {
                var rows=10;
                //放入数据
                putIn(data);
                scrollTo(0,0);//回到顶部
                //分页
                $("#pagination").paging({
                    rows:rows,//每页显示条数
                    pageNo:currentPage,//当前所在页码
                    totalPage:data[data.length-1]['totalPage'],//总页数
                    totalSize:data[data.length-1]['totalSize'],//总记录数
                    callback:function(currentPage){
                        submitInfo(currentPage);
                    }
                })
                // $(".apply").click(function(){
                //     var oName=$(this).parent().parent().find('strong').text();
                //     var uName=$("#username").text();
                //     $.ajax({
                //         type: 'POST',
                //         url: '/',
                //         dataType: 'json',
                //         data: {
                //             "oName":oName,
                //             "uName":uName
                //         },
                //         success:function(data){
                //             alert(data);
                //         },
                //         error:function(){
                //             alert("请求失败");
                //         }
                //     });
                // })
            },
            error: function () {
                alert("获取消息失败");
            }

        })
}


//金额排序类
function sortAmount(currentPage){
    $.ajax({
            type:'POST',
            url:'/sort/sortByAmount',
            dataType:'json',
            data:{
                rows:"10",
                pageNo:currentPage,
                "mySort":'amountSort'

            },
            success: function (data) {
                var rows=10;
                //放入数据
                putIn(data);
                scrollTo(0,0);//回到顶部
                //分页
                $("#pagination").paging({
                    rows:rows,//每页显示条数
                    pageNo:currentPage,//当前所在页码
                    totalPage:data[data.length-1]['totalPage'],//总页数
                    totalSize:data[data.length-1]['totalSize'],//总记录数
                    callback:function(currentPage){
                        sortAmount(currentPage);
                    }
                })
                // $(".apply").click(function(){
                //     var oName=$(this).parent().parent().find('strong').text();
                //     var uName=$("#username").text();
                //     $.ajax({
                //         type: 'POST',
                //         url: '/',
                //         dataType: 'json',
                //         data: {
                //             "oName":oName,
                //             "uName":uName
                //         },
                //         success:function(data){
                //             alert(data);
                //         },
                //         error:function(){
                //             alert("请求失败");
                //         }
                //     });
                // })
            },
            error: function () {
                alert("获取消息失败");
            }

        })
}


//时间排序类
function sortTime(currentPage) {
    $.ajax({
        type: 'POST',
        url: '/sort/sortByTime',
        dataType: 'json',
        data: {
            rows: "10",
            pageNo: currentPage,
            "mySort": 'timeSort'

        },
        success: function (data) {
            var rows = 10;
            //放入数据
            putIn(data);
            scrollTo(0,0);//回到顶部
            //分页
            $("#pagination").paging({
                rows: rows,//每页显示条数
                pageNo: currentPage,//当前所在页码
                totalPage: data[data.length - 1]['totalPage'],//总页数
                totalSize: data[data.length - 1]['totalSize'],//总记录数
                callback: function (currentPage) {
                    sortTime(currentPage);
                }
            })
            // $(".apply").click(function(){
            //     var oName=$(this).parent().parent().find('strong').text();
            //     var uName=$("#username").text();
            //     $.ajax({
            //         type: 'POST',
            //         url: '/',
            //         dataType: 'json',
            //         data: {
            //             "oName":oName,
            //             "uName":uName
            //         },
            //         success:function(data){
            //             alert(data);
            //         },
            //         error:function(){
            //             alert("请求失败");
            //         }
            //     });
            // })
        },
        error: function () {
            alert("获取消息失败");
        }


    })
}
//测试
// var data=[{"name":"yyq","state":"报名中"},{"name":"zhy","state":"进行中"},{"name":"www","state":"已完成"}];
// myPutIn(data);
//
// checkOut();
// function checkOut(){
//     var sec=$("section");
//     sec.click(function(){
//         var oSec=$(this);
//         var oName=oSec.find("strong").text();
//         alert(oName);
//     })
// }
// function myPutIn(data){
//     put_word.html(clear);
//     $.each(data, function (index, obj) {
//         if (index != (data.length)) {
//             var sec = $('<section class="row"></section>');
//             // 添加标题
//             var title = $('<p class="h4"></p>');
//             title.append('<span class="badge">'+obj['state']+'</span>'+'<strong>' + obj['name'] + '</strong>');
//             sec.append(title);
//             put_word.append(sec);
//         }
//     });
//
//
// }






