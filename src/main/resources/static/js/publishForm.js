/**
 * Created by 杨玉卿 on 2018/4/7.
 */
var myCount=0;
$('#rating').star({
    modus:'entire', //点亮模式
    total:5, //几颗星
    num:0, //默认点亮个数
    readOnly:false,//是否只读
    chosen:function(count,total){
        myCount=count;
//                alert("您的评分是"+myCount);
    }
});
//选择时间
$("#datetimepicker8").datetimepicker({
    minView:2,
    maxView:3,
    todayBtn:true,
    todayHighlight:true,
    pickerPosition:'bottom-left',
    format:'yyyy/mm/dd',
    autoclose:true,
    showMeridian:true,
});
$("#datetimepicker9").datetimepicker({
    minView:2,
    maxView:3,
    todayBtn:true,
    todayHighlight:true,
    pickerPosition:'bottom-left',
    format:'yyyy/mm/dd',
    autoclose:true,
});
$(function () {
    $('#datetimepicker8').datetimepicker();
    $('#datetimepicker9').datetimepicker({
        useCurrent: false //Important! See issue #1075
    });
    $("#datetimepicker8").on("dp.change", function (e) {
        $('#datetimepicker9').data("DateTimePicker").minDate(e.date);
    });
    $("#datetimepicker9").on("dp.change", function (e) {
        $('#datetimepicker8').data("DateTimePicker").maxDate(e.date);
    });
});
$("#mySubmit").click(function(event){

    event.preventDefault(); // 标准浏览器

    //提交上交时间，时间戳格式
    var date=new Date();
    var stampTime=date.getTime();
    var publishTime=document.getElementById("publishTime");
    publishTime.value=stampTime;
    //提交发布者
    var publisherValue=$("#personName").text();
    var publisher=document.getElementById("publisher");
    publisher.value=publisherValue;
    //提交安全等级
    var rank=document.getElementById("rank");
    rank.value=myCount;
    //数字的正则表达式
    var pattern=/\d+/;
    var money_value=$("#money").val();
    if($("#myName").val()==""){
        alert("请填写项目名称~");
    }
    else if($("#rank").val()==0){
        alert("请确定项目安全等级~");
    }
    else if($("#content").val()==""){
        alert("请简述项目内容~");
    }
    else if($("#inputFile").val()==""){
        alert("请上传项目实施计划书~");
    }
    else if($("#enterTime").val()==""){
        alert("请确定报名截止时间~");
    }
    else if($("#finishedTime").val()==""){
        alert("请确定项目截止时间~");
    }
    else if($(":radio[name='category']").is(":checked")==false){
        alert("请至少选择一个技术类别~");
    }
    else if($("#requirement").val()==""){
        alert("请填写技术要求~");
    }
    else if($("#money").val()==""){
        alert("请填写项目金额~");
    }
    else if(!pattern.test(money_value)){
        alert("项目金额请用阿拉伯数字填写~");
    }
    else{
        $("#publishForm").submit();
    }
})