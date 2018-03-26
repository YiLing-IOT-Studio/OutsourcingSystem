/**
 * Created by 杨玉卿 on 2018/3/23.
 */
var oBtn1=$(".addAll");
var oTask=$("#task");
oBtn1.click(function(){
    var oDiv=$("<div class='oneTask page2 col-md-offset-2 col-md-8'></div>");
    //添加p1
    var oP1=$("<p class='text-center taskTitle'></p>");
    oP1.append("<label>项目名</label>"+"<br/>"+
        "<input type='text' class='taskName'/>"+"<br/>");
    oDiv.append(oP1);

    //添加p2
    var oP2=$("<p class='task'></p>");
    oP2.append("<label>任务总述</label>"+"</br>"+
        "<textarea placeholder='任务总述' class='taskContent'></textarea>"+"<br/>");
    oDiv.append(oP2);

    //添加p3
    var oP3=$("<p class='text-right'></p>");
    oP3.append("<button type='button' class='btn btn-default btn-sm addTask'>" +
        "<span class='glyphicon glyphicon-th-list'></span> 添加子任务</button>"+"<br/>"
    );
    oDiv.append(oP3);
    //保存并上传
    var oP4=$("<p class='text-right'></p>");
    oP4.append("<button type='button' class='btn btn-default btn-sm upload'>"+
        "<span class='glyphicon glyphicon-upload'></span> 保存并上传</button>"+"<br/>"
    );
    oDiv.append(oP4);
    oTask.append(oDiv);
    var cnt=0;
    $(".addTask").click(function(){
        //添加分任务
        var preTask=$(this).parent().siblings(".task");
        cnt=cnt+1;
        preTask.append("<label>子任务</label>"+"<span>"+cnt+"</span>"+"</br>"+
            "<textarea placeholder='任务描述' class='taskDev'></textarea>"+"<br/>"+"<p class='text-right'>"+
            "<label>任务负责人</label>"+"<input type='text' class='staffName'/>"+"</p>");
        $(".upload").click(function(){
            //获取当前的任务名、任务内容、分任务、分任务负责人都是什么
            var taskName=$(this).parent().siblings(".taskTitle").children(".taskName");
            console.log(taskName.val());
            var taskContent=$(this).parent().siblings(".task").children(".taskContent");
            console.log(taskContent.val());
            var taskDev=$(this).parent().siblings(".task").children(".taskDev");
            console.log(taskDev.val());
            var staffName=$(this).parent().siblings(".task").children(".staffName");
            console.log(staffName.val());
        });
    });


});
