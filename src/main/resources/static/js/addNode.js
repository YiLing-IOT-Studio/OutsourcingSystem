/**
 * Created by 杨玉卿 on 2018/3/23.
 */
$(document).ready(function(){
    $(document).ajaxSend(function(e, xhr, options) {
        //从cookie里取出token值
        var token = $.cookie('token');
        var header = 'X-CSRF-TOKEN';
        xhr.setRequestHeader(header, token);
    });
var oBtn1=$(".addAll");
var oTask=$("#task");
oBtn1.click(function(){
    var oDiv=$("<div class='oneTask page2 col-md-offset-2 col-md-8'></div>");
    //taskTitle
    var oP1=$("<p class='text-center taskTitle'></p>");
    oP1.append("<label>项目名</label>"+"<br/>"+
        "<input type='text' class='taskName'/>"+"<br/>");
    oDiv.append(oP1);

    //taskContent
    var oP2=$("<p class='task'></p>");
    oP2.append("<label>任务总述</label>"+"</br>"+
        "<textarea placeholder='任务总述' class='taskContent'></textarea>"+"<br/>");
    oDiv.append(oP2);

    //taskBtn
    var oP3=$("<p class='text-right'></p>");
    oP3.append("<button type='button' class='btn btn-default btn-sm addTask'>" +
        "<span class='glyphicon glyphicon-th-list'></span> 添加子任务</button>"+"<br/>"
    );
    oDiv.append(oP3);
    //uploadBtn
    var oP4=$("<p class='text-right'></p>");
    oP4.append("<button type='button' class='btn btn-default btn-sm upload'>"+
        "<span class='glyphicon glyphicon-upload'></span> 保存并上传</button>"+"<br/>"
    );
    oDiv.append(oP4);
    oTask.append(oDiv);
    var cnt=0;
    //taskBtn-event
    $(".addTask").click(function(){
        //findTaskContent-putin
        var preTask=$(this).parent().siblings(".task");
        cnt=cnt+1;
        //taskDev&&taskStaff
        preTask.append("<label>子任务</label>"+"<span>"+cnt+"</span>"+"</br>"+
            "<textarea placeholder='任务描述' class='taskDev'></textarea>"+"<br/>"+"<div class='text-right'>"+
            "<label>任务负责人</label>"+"<input type='text' class='staffName'/>"+"</div>");

    });
    $(".upload").click(function(){
        //获取当前的任务名、任务内容、分任务、分任务负责人都是什么
        var taskName=$(this).parent().siblings(".taskTitle").find(".taskName");
        console.log(taskName.val());
        var taskContent=$(this).parent().siblings(".task").find(".taskContent");
        console.log(taskContent.val());
        if(taskName.val()==""||taskContent.val()==""){
            alert("请填写项目名和任务总述~");
        }
        else {
            var taskDev = $(this).parent().siblings(".task").find(".taskDev");
            var array1 = '';
            for (var i = 0; i < taskDev.length; i++) {
                array1 += $(taskDev[i]).val() + ",";

            }
            console.log(array1);
            var staffName = $(this).parent().siblings(".task").find(".staffName");
            var array2 = '';
            for (var j = 0; j < staffName.length; j++) {
                array2 += $(staffName[j]).val() + ",";

            }
            console.log(array2);
            $.ajax({
                type: 'POST',
                url: '/project',
                dataType: 'json',
                data: {
                    'taskName': taskName.val(),
                    'taskContent': taskContent.val(),
                    'taskDev': array1,
                    'staffName': array2
                },
                success: function (data) {
                    if (parseInt(data) == 1) {
                        alert("上传成功！");
                        var oDiv=$(this).parent().parent();
                        oDiv.hide();
                    }
                    else {
                        alert("上传无效！");
                    }
                },
                error: function () {
                    alert("请求失败了...");
                }
            });

        }
    });


});
});