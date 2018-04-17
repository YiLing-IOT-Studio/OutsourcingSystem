/**
 * Created by 杨玉卿 on 2018/3/23.
 */

var oBtn1=$(".addAll");
var oTask=$("#task");
oBtn1.click(function(){
    oTask.html("");
    var oDiv=$('<div class="oneTask page2 col-md-offset-2 col-md-8"></div>');
    //taskTitle
    var oP1=$("<p class='taskTitle'></p>");
    oP1.append("<label>项目名</label>"+"<br/>"+
        "<input type='text' class='projectName form-control' name='projectName'/>"+"<br/>");
    oDiv.append(oP1);
    //taskName
    var oP=$("<p class='text-left'></p>");
    oP.append('<label>任务名</label>'+'<br/>'+ "<input type='text' class='taskName form-control' name='taskName'/>"+"<br/>");
    oDiv.append(oP);

    //task
    var oP2=$("<p class='task'></p>");
    //deadLine
    var oDate=$("<div></div>");
    oDate.append('<label>截止日期：</label>'+
        '<div class="form-group">'+
        "<div class='input-group date'  id='datetimepicker1' >"+
        "<input type='text' class='form-control' id='deadLine' name='missionDeadLine'/>"+
        '<span class="input-group-addon">'+
        '<span class="glyphicon glyphicon-calendar"></span>'+
        '</span>'+
        '</div>'+
        "</div>");
    oP2.append(oDate);
    oP2.append("<div class='taskSon'>"+"<label>任务</label>"+"<br/>"+
               "<textarea placeholder='任务描述' class='taskDev' name='taskContent'></textarea>"+"<br/>"
              + "<div class='text-right task-grade'>"+ "<span class='grade'>任务等级&nbsp;&nbsp;</span>"+"<label class='radio-inline'>"+
                "<input type='radio' value='低' name='authority'/>"+"低</label>"+"<label class='radio-inline'>"+
                "<input type='radio' value='中' name='authority'/>"+"中</label>"+"<label class='radio-inline'>"+
                "<input type='radio' value='高' name='authority'/>"+"高</label>"+"<br/>"
              +"</div>"
    );
    oDiv.append(oP2);
    //uploadBtn
    var oP5=$("<p class='text-right'></p>");
    oP5.append("<button type='submit' class='btn btn-default btn-sm upload'>"+
        "<span class='glyphicon glyphicon-upload'></span> 立即发布</button>"+"<br/>"
    );
    oDiv.append(oP5);
    oTask.append(oDiv);
    //date
    $("#datetimepicker1").datetimepicker({
        minView:2,
        maxView:3,
        todayBtn:true,
        todayHighlight:true,
        pickerPosition:'bottom-left',
        format:'yyyy/mm/dd',
        autoclose:true,
        showMeridian:true
    });

    $('#datetimepicker10').datetimepicker();
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
    $("#deadLine").attr('value',myStr);
    $(".upload").click(function(event) {
        event.preventDefault();
        //获取当前的任务名、任务内容、分任务、分任务等级都是什么
        var projectName = $(".projectName");
        console.log(projectName.val());
        var taskName = $(".taskName");
        console.log(taskName.val());
        var taskDev = $(".taskDev");
        console.log(taskDev.val());
        var taskGrade = $(":radio[name='authority']");
        var val=$('input:radio[name="authority"]:checked').val();
        console.log(val);
        if (projectName.val() == "") {
            alert("请填写项目名~");

        }
        else if (taskName.val() == "") {
            alert("请填写任务名");

        }
        else if (taskName.val().length > 10) {
            alert("任务名称请描述在10个字符以内");


        }
        else if (taskDev.val() == "") {
            alert("请填写任务描述~");

        }
        else if (taskGrade.is(":checked") == false) {
            alert("请选择任务等级");
        }
        else {
           var form = new FormData(task);
            $.ajax({
                type:"post",
                url:"/releaseTask",
                dataType:"json",
                data:form,
                processData: false,
                contentType: false,
                success:function(data){
                    if(data==1){
                        alert("发布成功");
                    }
                    else{
                        alert("发布失败");
                    }
                },
                error:function(){
                    alert("请求发布失败");
                }
            });
        }
    });



});
//date
$("#datetimepicker1").datetimepicker({
    minView:2,
    maxView:3,
    todayBtn:true,
    todayHighlight:true,
    pickerPosition:'bottom-left',
    format:'yyyy/mm/dd',
    autoclose:true,
    showMeridian:true
});

    $('#datetimepicker10').datetimepicker();
    // var time=new Date();
    // var myStr;
    // var myMonth=time.getMonth()+1;
    // if(myMonth<10){
    //     myMonth="0"+myMonth;
    // }
    // var myDate=time.getDate();
    // if(myDate<10){
    //     myDate="0"+myDate;
    // }
    // myStr=time.getFullYear()+"/"+myMonth+"/"+myDate;
    // $("#deadLine").attr('value',myStr);

