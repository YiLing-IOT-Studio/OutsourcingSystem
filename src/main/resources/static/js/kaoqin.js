/**
 * Created by 杨玉卿 on 2018/3/17.
 */
//选择时间
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
$("#datetimepicker6").datetimepicker({
    minView:2,
    maxView:3,
    todayBtn:true,
    todayHighlight:true,
    pickerPosition:'bottom-left',
    format:'yyyy/mm/dd',
    autoclose:true,
    showMeridian:true
});
$("#datetimepicker7").datetimepicker({
    minView:2,
    maxView:3,
    todayBtn:true,
    todayHighlight:true,
    pickerPosition:'bottom-left',
    format:'yyyy/mm/dd',
    autoclose:true
});
$(function () {
    $('#datetimepicker6').datetimepicker();
    $('#datetimepicker7').datetimepicker({
        useCurrent: false //Important! See issue #1075
    });
    $("#datetimepicker6").on("dp.change", function (e) {
        $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
    });
    $("#datetimepicker7").on("dp.change", function (e) {
        $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
    });
});
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

            //姓名
            var kName= $('<td></td>');
            kName.append(obj['username']);
            tr.append(kName);

            //签到时间
            var kSignIn= $('<td></td>');
            kSignIn.append(obj['come_time']);
            tr.append(kSignIn);

            //签退时间
            var kSignOut= $('<td></td>');
            kSignOut.append(obj['leave_time']);
            tr.append(kSignOut);

            //总时间
            var kTotal= $('<td></td>');
            kTotal.append(obj['str_time']);
            tr.append(kTotal);

            kBody.append(tr);
        }
    })
}
//可查看时间段的签到签退情况
$("#checkTime").click(function(){
    var sTime=$("#start").val();
    var eTime=$("#end").val();
    $.ajax({
        type:"POST",
        url:"/getSignRecords",
        dataType:"json",
        data:{
            sTime:sTime,
            eTime:eTime
        },
        success:function(data){
            fillData(data);
        },
        error:function(){
            alert("请求失败！");
        }
    })


})