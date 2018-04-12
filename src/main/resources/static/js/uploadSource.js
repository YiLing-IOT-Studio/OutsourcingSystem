/**
 * Created by 杨玉卿 on 2018/4/12.
 */
$("#uplBtn").click(function(event){
    event.preventDefault();
    if($("#fileName").val()=="项目名"){
        alert("请填写项目名！");
    }
    else if($("#fileDescription").val()=="文件描述"){
        alert("请填写关于该文件的描述！");
    }
    else if($("#upl").val()==""){
        alert("请选择上传文件");
    }
    else{
        alert("上传成功！");
        $("#uploadForm").submit();
    }
});