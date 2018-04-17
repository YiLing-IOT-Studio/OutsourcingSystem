/**
 * Created by 杨玉卿 on 2018/3/17.
 */
//选择时间
$("#datetimepicker6").datetimepicker({
    minView:2,
    maxView:3,
    todayBtn:true,
    todayHighlight:true,
    pickerPosition:'bottom-left',
    format:'yyyy/mm/dd',
    autoclose:true,
    showMeridian:true,
});
$("#datetimepicker7").datetimepicker({
    minView:2,
    maxView:3,
    todayBtn:true,
    todayHighlight:true,
    pickerPosition:'bottom-left',
    format:'yyyy/mm/dd',
    autoclose:true,
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
    var time=new Date();
    var myStr;
    var myMonth=time.getMonth()+1;
    if(myMonth<10){
        myMonth="0"+myMonth;
    }
    var myDate=time.getDate();
    if(myDate<10){
        myDate="0"+myDate;
    }
    myStr=time.getFullYear()+"/"+myMonth+"/"+myDate;
    $("#start").attr('value',myStr);
    $("#end").attr('value',myStr);
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
            kName.append(obj['phone']);
            tr.append(kName);

            //签到时间
            var kSignIn= $('<td></td>');
            var date=new Date(obj['come_time']);
            var Y= date.getFullYear() + '/';
            var M= (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '/';
            var D = date.getDate() + ' ';
            var h = date.getHours() + ':';
            var m = date.getMinutes() + ':';
            var s = date.getSeconds();
            var signInTime=Y+M+D+h+m+s;
            kSignIn.append(signInTime);
            tr.append(kSignIn);

            //签退时间
            var kSignOut= $('<td></td>');
            var date=new Date(obj['leave_time']);
            var Y= date.getFullYear() + '/';
            var M= (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '/';
            var D = date.getDate() + ' ';
            var h = date.getHours() + ':';
            var m = date.getMinutes() + ':';
            var s = date.getSeconds();
            var signOutTime=Y+M+D+h+m+s;
            kSignOut.append(signOutTime);
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
            alert("考勤情况查询请求失败！");
        }
    })


})