/**
 * Created by yyq on 2018/3/1.
 */
var put_project=$("#put_project");
//页面加载时请求，将内容填充到页面
function ajaxTest() {
    $.ajax({
        type: 'POST',
        url: '/checkproject',
        dataType: 'json',
        data: {
            message:'填充页面'
        },
        success: function (data) {
            put_project.innerHTML = '';
            $.each(data, function (index, obj) {
                if (index != (data.length - 1)) {
                    var sec = $('<section></section>');
                    // 添加标题
                    var Uid = $('<p class="h5"></p>');
                    Uid.append('<strong>' + obj['state'] + '</strong>');
                    sec.append(Uid);
                    // 添加主体
                    var SelfIntro = $('<div></div>');
                    SelfIntro.append(obj['name'] + '<br/>');
                    sec.append(SelfIntro);
                    //添加其余详细信息
                    var Details = $('<p></p>');
                    Details.append(obj['type'] + '&nbsp;&nbsp;&nbsp;/&nbsp;' + obj[' category'] + '&nbsp;&nbsp;&nbsp;/&nbsp;' + obj['content']);
                    sec.append(Details);
                    put_project.append(sec);
                    // var message = $('<div class="row">' +
                    //     '<h4>' +
                    //     '<span class="badge">' + obj['state'] + '</span>' +
                    //     obj['name'] +
                    //     '</h4>' +
                    //     '<span class="label label-default">' + json[i].type + '</span>' + '<span class="label label-default">' + json[i].category + '</span>' +
                    //     '<p>' + json[i].content + '</p>' +
                    //     '<p class="text-right">' + '<i class="fa fa-user-o">' + '</i>' + json[i].number + '</p>' +
                    //     '<p>' + '<i class="fa fa-user-circle">' + '</i>' + '&nbsp;&nbsp;' + json[i].publisher + '&nbsp;&nbsp;&nbsp;&nbsp;' + json[i].time + '&nbsp;&nbsp;&nbsp;&nbsp;' + json[i].requirment + '</p>'
                    //     + '<div class="progress">' +
                    //     '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width:' + json[i].progress +'%'+ ';">' +
                    //     json[i].progress +'%'+
                    //     '</div>'
                    //     + '</div>'
                    //     + '</div>');
                    // put_project.append(message);
                }
            });
        },
        error: function () {
            alert("获取消息失败");
        }
    });
}
ajaxTest();
// //搜索项目，刷新内容
// var search=document.getElementById("search");
// search.onkeyup(function(){
//     search_text=search.val();
//
//     $.ajax({
//         type: "POST",
//         url: "/checkproject",
//         dataType: 'json',
//         data: {
//             search_text:search_text
//         },
//         success: function (data) {
//             var json=eval('('+data+')');
//             put_project.innerHTML="";
//             for (var i = 0; i < json[i].length; i++) {
//                 var message = $('<div class="row">' +
//                     '<h4>' +
//                     '<span class="badge">' + json[i].state + '</span>' +
//                     json[i].name +
//                     '</h4>' +
//                     '<span class="label label-default">' + json[i].type + '</span>' + '<span class="label label-default">' + json[i].category + '</span>' +
//                     '<p>' + json[i].content + '</p>' +
//                     '<p class="text-right">' + '<i class="fa fa-user-o">' + '</i>' + json[i].number + '</p>' +
//                     '<p>' + '<i class="fa fa-user-circle">' + '</i>' + '&nbsp;&nbsp;' + json[i].publisher + '&nbsp;&nbsp;&nbsp;&nbsp;' + json[i].time + '&nbsp;&nbsp;&nbsp;&nbsp;' + json[i].requirment + '</p>'
//                     + '<div class="progress">' +
//                     '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width:' + json[i].progress +'%'+ ';">' +
//                     json[i].progress +'%'+
//                     '</div>'
//                     + '</div>'
//                     + '</div>');
//                 put_project.append(message);
//
//             }
//
//         },
//         error: function (data) {
//
//         }
//     });
// });
// //提交多选框组，刷新内容
// var sure_submit=document.getElementById("sure_submit");
// sure_submit.onclick(function(){
//     var category_checkbox=document.getElementsByName("category");
//     var state_radio=document.getElementsByName("state");
//     var amount_radio=document.getElementsByName("amount");
//     var type_radio=document.getElementsByName("type");
//     var category_array=[];
//     var state="";
//     var amount="";
//     var type="";
//     for(var i=0;i<category_checkbox.length;i++){
//         if(category_checkbox[i].checked==true){
//             category_array.push(category_checkbox[i].value);
//         }
//     }
//     for(var j=0;j<state_radio.length;j++){
//         if(state_radio[j].checked==true){
//             state=state_radio[j].value;
//         }
//     }
//     for(var k=0;k<amount_radio.length;k++){
//         if(amount_radio[k].checked==true){
//             amount=amount_radio[k].value;
//         }
//     }
//     for(var m=0;m<type_radio.length;m++){
//         if(type_radio[m].checked==true){
//             type=type_radio[m].value;
//         }
//     }
//     $.ajax({
//         type: "POST",
//         url: "/checkproject",
//         dataType: 'json',
//         data: {
//             category:category_array,
//             state:state,
//             amount:amount,
//             type:type
//         },
//         success: function (data) {
//             var json=eval('('+data+')');
//             put_project.innerHTML="";
//             for (var i = 0; i < json[i].length; i++) {
//                 var message = $('<div class="row">' +
//                     '<h4>' +
//                     '<span class="badge">' + json[i].state + '</span>' +
//                     json[i].name +
//                     '</h4>' +
//                     '<span class="label label-default">' + json[i].type + '</span>' + '<span class="label label-default">' + json[i].category + '</span>' +
//                     '<p>' + json[i].content + '</p>' +
//                     '<p class="text-right">' + '<i class="fa fa-user-o">' + '</i>' + json[i].number + '</p>' +
//                     '<p>' + '<i class="fa fa-user-circle">' + '</i>' + '&nbsp;&nbsp;' + json[i].publisher + '&nbsp;&nbsp;&nbsp;&nbsp;' + json[i].time + '&nbsp;&nbsp;&nbsp;&nbsp;' + json[i].requirment + '</p>'
//                     + '<div class="progress">' +
//                     '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width:' + json[i].progress +'%'+ ';">' +
//                     json[i].progress +'%'+
//                     '</div>'
//                     + '</div>'
//                     + '</div>');
//                 put_project.append(message);
//
//             }
//         },
//         error: function (data) {
//
//         }
//     });
// });
// //排序项目，刷新内容
// var amount_sort=document.getElementById("amount_sort");
// var time_sort=document.getElementById("time_sort");
// amount_sort.onclick(function(){
//     $.ajax({
//         type: "POST",
//         url: "/checkproject",
//         dataType: 'json',
//         data: {
//             sort_text:'价格排序'
//         },
//         success: function (data) {
//             var json=eval('('+data+')');
//             put_project.innerHTML="";
//             for (var i = 0; i < json[i].length; i++) {
//                 var message = $('<div class="row">' +
//                     '<h4>' +
//                     '<span class="badge">' + json[i].state + '</span>' +
//                     json[i].name +
//                     '</h4>' +
//                     '<span class="label label-default">' + json[i].type + '</span>' + '<span class="label label-default">' + json[i].category + '</span>' +
//                     '<p>' + json[i].content + '</p>' +
//                     '<p class="text-right">' + '<i class="fa fa-user-o">' + '</i>' + json[i].number + '</p>' +
//                     '<p>' + '<i class="fa fa-user-circle">' + '</i>' + '&nbsp;&nbsp;' + json[i].publisher + '&nbsp;&nbsp;&nbsp;&nbsp;' + json[i].time + '&nbsp;&nbsp;&nbsp;&nbsp;' + json[i].requirment + '</p>'
//                     + '<div class="progress">' +
//                     '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width:' + json[i].progress +'%'+ ';">' +
//                     json[i].progress +'%'+
//                     '</div>'
//                     + '</div>'
//                     + '</div>');
//                 put_project.append(message);
//
//             }
//         },
//         error: function (data) {
//
//         }
//     });
// });
//
// time_sort.onclick(function(){
//     $.ajax({
//         type: "POST",
//         url: "/checkproject",
//         dataType: 'json',
//         data: {
//             search_text:'时间排序'
//         },
//         success: function (data) {
//             var json=eval('('+data+')');
//             put_project.innerHTML="";
//             for (var i = 0; i < json[i].length; i++) {
//                 var message = $('<div class="row">' +
//                     '<h4>' +
//                     '<span class="badge">' + json[i].state + '</span>' +
//                     json[i].name +
//                     '</h4>' +
//                     '<span class="label label-default">' + json[i].type + '</span>' + '<span class="label label-default">' + json[i].category + '</span>' +
//                     '<p>' + json[i].content + '</p>' +
//                     '<p class="text-right">' + '<i class="fa fa-user-o">' + '</i>' + json[i].number + '</p>' +
//                     '<p>' + '<i class="fa fa-user-circle">' + '</i>' + '&nbsp;&nbsp;' + json[i].publisher + '&nbsp;&nbsp;&nbsp;&nbsp;' + json[i].time + '&nbsp;&nbsp;&nbsp;&nbsp;' + json[i].requirment + '</p>'
//                     + '<div class="progress">' +
//                     '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width:' + json[i].progress +'%'+ ';">' +
//                     json[i].progress +'%'+
//                     '</div>'
//                     + '</div>'
//                     + '</div>');
//                 put_project.append(message);
//
//             }
//         },
//         error: function (data) {
//
//         }
//     });
// });




