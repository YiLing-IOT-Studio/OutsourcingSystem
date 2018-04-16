/**
 * Created by 杨玉卿 on 2018/4/12.
 */
//测试用
// for(var i=0;i<10;i++){
//     var oDiv=$("select");
//     var my_option=$("<option></option>");
//     my_option.append(i);
//     // my_option.attr('value',i);
//     // console.log(my_option.value);
//     oDiv.append(my_option);
// }
$("#myTaskName").focus(function(){
    var oDiv=$("#myTaskName");
    oDiv.html("");
    var projectName=$("#projectName").val();
    console.log(projectName);
    $.ajax({
        type:"get",
        url:"/resource/getTaskNameByProjectName",
        dataType:"json",
        async:false,
        data:{
            'projectName':projectName
        },
        beforeSend:function(){},
        success:function(data){
            var oDiv=$("#myTaskName");
            oDiv.html("");
            for(var i in data){
                var my_option=$("<option></option>");
                my_option.append(data[i]);
                oDiv.append(my_option);
            }
        },
        error:function(){
            alert("获取任务失败！");
        }

    });
});
$("#uplBtn").click(function(event){
    event.preventDefault();
    if($("#projectName").val()==""){
        alert("请填写项目名！");
    }
    else if($("#myTaskName").val()==null){
        alert("请选择任务名称！");
    }
    else if($("#upl").val()==""){
        alert("请选择上传文件");
    }
    else{
        var form = new FormData(uploadForm);
        $.ajax({
            type:"post",
            url:"/resource/releaseResource",
            dataType:"json",
            data:form,
            async:false,
            processData: false,
            contentType: false,
            beforeSend:function(){
                showFun();
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
//判断文件格式
function fileChange(target) {
    var len=target.files.length;
//                alert(len);

    var i;
    for(i=0;i<len;i++){
        var name=target.files[i].name;
//                    alert(name);
        var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
        if(fileName !="jpg" && fileName !="bmp" && fileName !="jpg" && fileName !="png" && fileName !="gif" && fileName !="tif"&&fileName !="psd"&&fileName !="raw"
            &&fileName !="avi"&&fileName !="mov"&&fileName !="asf"&&fileName !="wmv"&&fileName !="navi"&&fileName !="3gp"&&fileName !="mkv"&&fileName !="flv"
            &&fileName !="doc"&&fileName !="wps"&&fileName !="xls"&&fileName !="txt"&&fileName !="ppt"&&fileName !="rar"&&fileName !="htm"&&fileName !="pdf"&&fileName !="dwg"){
            alert("文件格式不支持："+fileName+"\n请选择以下格式上传文件:\n图片文件格式(jpg,png,gif,dwg,pdf,gif)！\n"+
                "视频文件格式(avi，mov，asf，wmv，navi，3gp，mkv，flv)！\n"+
                "文档文件格式(doc,wps,xls,ppt,txt,rar,htm,pdf,dwg)\n");
            target.value="";
        }
    }
}
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
