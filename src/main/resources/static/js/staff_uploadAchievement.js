/**
 * Created by 杨玉卿 on 2018/4/10.
 */
var video=document.getElementById('video');
video.pause();
$("#video").css("visibility","hidden");
$("#uplBtn").click(function(event){
    var pattern=/^\d{1,3}$/;
    event.preventDefault();
    var progress_value=$("#progress").val();
    if($("#projectName").val()==""){
        alert("请填写项目的名字！");
    }
    else if($("#fileDescription").val()==""){
        alert("请填写关于该文件的描述！");
    }
    else if($("#progress").val()==""){
        alert("请填写目前项目总进度");
    }
    else if(!pattern.test(progress_value)||progress_value>100){
        alert("项目进度的值只可填0~100整数的格式~");
    }

    else if($("#upl").val()==""){
        alert("请选择上传文件");
    }
    else{
        var form = new FormData(uploadAchievement);
        $.ajax({
            type:"post",
            url:"/submissionWork",
            dataType:"json",
            data:form,
            processData: false,
            contentType: false,
            beforeSend:function(xhr){
                xhr.setRequestHeader('csrf_');
                showFun();
            },
            headers: {
                'csrf_':$("meta[name='_csrf']").attr("content")
            },
            success:function(data){
                hideFun();
                if(data==1){
                    alert("上传成功");
                }
                else{
                    alert("上传失败");
                }
            },
            error:function(){
                hideFun();
                alert("请求上传资料失败");
            }
        })
    }
});
//loading...
function showFun() {
    var info = document.getElementById("loading");
    var bg = document.getElementById("bg");
    info.style.display = "block";
    bg.style.display = "block";

}
function hideFun(){
    var info = document.getElementById("loading");
    var bg = document.getElementById("bg");
    info.style.display = "none";
    bg.style.display = "none";
}