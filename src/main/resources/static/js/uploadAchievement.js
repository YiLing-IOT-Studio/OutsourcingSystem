/**
 * Created by 杨玉卿 on 2018/4/10.
 */
$("#uplBtn").click(function(event){
    var pattern=/^\d{1,3}$/;
    event.preventDefault();
    var progress_value=$("#progress").val();
    alert(progress_value);
    if($("#fileName").val()=="项目名"){
        alert("请填写项目的名字！");
    }
    else if($("#fileDescription").val()=="文件描述"){
        alert("请填写关于该文件的描述！");
    }
    else if($("#progress").val()=="项目进度"){
        alert("请填写目前项目总进度");
    }
    else if(!pattern.test(progress_value)||progress_value>100){
        alert("项目进度的值只可填0~100整数的格式~");
    }

    else if($("#upl").val()==""){
        alert("请选择上传文件");
    }
    else{
        alert("上传成功！");
        $("#uploadForm").submit();
    }
});