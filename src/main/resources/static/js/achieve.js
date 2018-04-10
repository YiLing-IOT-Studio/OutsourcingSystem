/**
 * Created by 杨玉卿 on 2018/4/10.
 */
$("#uplBtn").click(function(event){
    event.preventDefault();
    if($("#fileName").val()=="文件名"){
        alert("请填写上传文件的名字！");
    }
    else if($("#fileDescription").val()=="文件描述"){
        alert("请填写关于该文件的描述！");
    }
    else if($("#progress").val()=="项目进度"){
        alert("请填写目前项目总进度");
    }
    else if($("#upl").val()==""){
        alert("请选择上传文件");
    }
    else{
        alert("上传成功！");
        $("#uploadForm").submit();
    }
})