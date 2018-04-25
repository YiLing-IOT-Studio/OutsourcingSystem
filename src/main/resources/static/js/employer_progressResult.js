/**
 * Created by 杨玉卿 on 2018/4/4.
 */
var oDiv=$("#chart");
var i;
$("#chart-tab").click(function(){
    $.ajax({
        type:"POST",
        url:"/statistice",
        dataType:"json",
        data:{},
        success:function(data){
            oDiv.html("");
            if(data.length==0){
                oDiv.html("<div class='alert alert-warning'>暂无项目进度统计信息，待任务负责人上传工作成果后可查看</div>");
            }
            for(i in data) {
                //项目名
                var oTitle = $("<h4></h4>");
                oTitle.append(data[i].name);
                oDiv.append(oTitle);
                //项目进度
                var oInfo=data[i]['info']+'%';
                var progress=$('<div class="progress"></div>');
                progress.append('<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width:2em;width:'+oInfo+';">'+oInfo+'</div>');
                oDiv.append(progress);
            }

        },
        error:function(){
            alert("项目进度情况请求失败！");
        }
    });

})