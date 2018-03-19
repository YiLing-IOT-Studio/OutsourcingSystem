/**
 * Created by yyq on 2018/3/1.
 */
//变量声明
var put_word=$("#put_word");
var mySearch=$("#search");
var amountSort=$("#amount_sort");
var timeSort=$("#time_sort");
var clear='';
//填充数据类
function putIn(data){
    put_word.html(clear);
    $.each(data, function (index, obj) {
        if (index != (data.length - 1)) {
            var sec = $('<section class="row"></section>');
            // 添加标题
            var title = $('<p class="h4"></p>');
            title.append('<span class="badge">'+obj['state']+'</span>'+'<strong>' + obj['name'] + '</strong>');
            sec.append(title);

            //标签
            var tags=$('<span class="label label-default">'+obj['type']+'</span>'+'<span class="label label-default">'+obj['category']+'</span>');
            sec.append(tags);

            // 主体
            var SelfIntro = $('<div></div>');
            SelfIntro.append(obj['content'] + '<br/>');
            sec.append(SelfIntro);

            //金额
            var Umoney = $('<p class="text-right"></p>');
            if(obj['amount']!=-1) {
                Umoney.append('<span class="money_text">'  + obj['amount']+ '元' + '</span>');
            }
            else{
                Umoney.append('<span class="money_text">' +'竞标报价'+ '</span>');
            }
            sec.append(Umoney);

            //其他信息
            var unixTimestamp=new Date(obj['time'].time);
            commonTime=unixTimestamp.toLocaleString();
            var Details=$('<p></p>');
            Details.append(obj['publisher']+'&nbsp;&nbsp;&nbsp;&nbsp;'+commonTime+'&nbsp;&nbsp;&nbsp;&nbsp;'+obj['requirement']);
            sec.append(Details);

            //进度条
            var fix_string=obj['progress']+'%';
            var progress=$('<div class="progress"></div>');
            progress.append('<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width:2em;width:'+fix_string+';">'+fix_string+'</div>');
            sec.append(progress);

            put_word.append(sec);
        }
    });


}

//页面请求类
function ajaxTest(currentPage) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
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
            })
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
                searchWord:keyWord
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
        //项目类型
        var typeRadios=$("input[name='_type']");
        var myType='';
        for(var g=0;g<typeRadios.length;g++){
            if(typeRadios[g].checked==true){
                myType=typeRadios[g].value;
            }
        }

        $.ajax({
            type:'POST',
            url:'/getAllOutsourcingInfo/classifySearch',
            dataType:'json',
            data:{
                rows:"10",
                pageNo:currentPage,
                myCategories:myCategories,
                myState:myState,
                myAmount:myAmount,
                myType:myType

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
                mySort:'amountSort'

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
            mySort: 'timeSort'

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
        },
        error: function () {
            alert("获取消息失败");
        }

    })
}







