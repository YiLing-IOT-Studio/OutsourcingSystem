/**
 * Created by 杨玉卿 on 2018/4/10.
 */
//填充数据类
function fillData(data){
    var kBody=$("#kBody");
    kBody.html('');
    $.each(data,function(index,obj){
        if (index != (data.length)) {
            var tr= $('<tr></tr>');

            //项目名称
            var kName= $('<td></td>');
            kName.append(obj['name']);
            tr.append(kName);

            //报名截止时间
            var kSignIn= $('<td></td>');
            var date=new Date(obj['registrationDeadline']);
            var Y= date.getFullYear() + '/';
            var M= (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '/';
            var D = date.getDate() + ' ';
            var h = date.getHours() + ':';
            var m = date.getMinutes() + ':';
            var s = date.getSeconds();
            var registrationDeadline=Y+M+D+h+m+s;
            kSignIn.append(registrationDeadline);
            tr.append(kSignIn);

            //项目截止时间
            var kSignOut= $('<td></td>');
            var date=new Date(obj['projectDeadline']);
            var Y= date.getFullYear() + '/';
            var M= (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '/';
            var D = date.getDate() + ' ';
            var h = date.getHours() + ':';
            var m = date.getMinutes() + ':';
            var s = date.getSeconds();
            var projectDeadline=Y+M+D+h+m+s;
            kSignOut.append(projectDeadline);
            tr.append(kSignOut);

            //项目分类
            var kTotal= $('<td></td>');
            kTotal.append(obj['category']);
            tr.append(kTotal);

            //状态
            var kState=$('<td></td>');
            kState.append(obj['state']);
            tr.append(kState);

            kBody.append(tr);
        }
    })
}
$("#li_xm_item").click(function(){
    $.ajax({
        type:"get",
        url:'/getOutsourcing',
        dataType:"json",
        data:{

        },
        success:function(data){
            fillData(data);
        },
        error:function(){
            alert("请求失败!");
        }

    })
});