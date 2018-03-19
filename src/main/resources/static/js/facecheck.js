/**
 * Created by 杨玉卿 on 2018/3/18.
 */
// var imgData = canvans.toDataURL();
// var imgData1 = imgData.substring(22);
// var upload=$("#upload");
// upload.click(function(){
//
//     $.ajax({
//         type:'POST',
//         url:'/getAllOutsourcingInfo/classifySearch',
//         dataType:'json',
//         data: {"img":imgData1,"username":username},
//     })
// })

var url=location.search;
var Request=new Object();
//如果在url中存在？号
if(url.indexOf("?")!==-1){
    var str=url.substr(1);
    Request[str.split("=")[0]]=str.split("=")[1];
}
alert(Request["username"]);

