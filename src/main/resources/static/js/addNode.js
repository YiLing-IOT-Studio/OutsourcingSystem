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
        "<textarea placeholder='任务总述'></textarea>"+"<br/>");
    oDiv.append(oP2);

    //添加p3
    var oP3=$("<p class='text-right'></p>");
    oP3.append("<button type='button' class='btn btn-default btn-sm addTask'>" +
        "<span class='glyphicon glyphicon-th-list'></span> 添加子任务</button>");
    oDiv.append(oP3);
    oTask.append(oDiv);
    var cnt=0;
    $(".addTask").click(function(){
        var preTask=$(this).parent().siblings(".task");
        cnt=cnt+1;
        preTask.append("<label>子任务</label>"+"<span>"+cnt+"</span>"+"</br>"+
            "<textarea placeholder='任务描述'></textarea>"+"<br/>"+"<p class='text-right'>"+
            "<button type='button' class='btn btn-default btn-sm addStaff'>"+
            "<span class='glyphicon glyphicon-plus-sign'></span> 添加负责人</button><br/>"+"</p>");



    });
    $(".addStaff").click(function(){

    })
});
