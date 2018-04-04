/**
 * Created by 杨玉卿 on 2018/4/4.
 */
var oDiv=$("#chart");
var i;
$("#chart-tab").click(function(){
    $.ajax({
        type:"POST",
        url:"/",
        dataType:"json",
        data:{},
        success:function(data){
            oDiv.html("");
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

        }
    });

})