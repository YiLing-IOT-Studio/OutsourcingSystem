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
            //序号
            var kId= $('<td></td>');
            kId.append(obj['id']);
            tr.append(kId);

            //项目名称
            var kName= $('<td></td>');
            kName.append(obj['username']);
            tr.append(kName);

            //报名截止时间
            var kSignIn= $('<td></td>');
            kSignIn.append(obj['come_time']);
            tr.append(kSignIn);

            //项目截止时间
            var kSignOut= $('<td></td>');
            kSignOut.append(obj['leave_time']);
            tr.append(kSignOut);

            //项目分类
            var kTotal= $('<td></td>');
            kTotal.append(obj['category']);
            tr.append(kTotal);

            kBody.append(tr);
        }
    })
}
$("#li_cg_item").click(function(){
    $.ajax({
        type:"get",
        url:'/staff_info',
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