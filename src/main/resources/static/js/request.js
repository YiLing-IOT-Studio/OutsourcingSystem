/**
 * Created by yyq on 2018/3/1.
 */
var put_project=document.getElementById("put_project");
$.ajax({
        type: "POST",
        url: "/checkproject",
        dataType: 'json',
        data: {},
        success: function (data) {
            function isEmptyObject(e) {
                var t;
                for (t in e)
                    return !1;
                return !0;
            }

            for (var i = 0; i < data.message.length; i++) {
                var message = $('<div class="row">' +
                    '<h4>' +
                    '<span class="badge">' + data.message[i].state + '</span>' +
                    data.message[i].name +
                    '</h4>' +
                    '<span class="label label-default">' + data.message[i].type + '</span>' + '<span class="label label-default">' + data.message[i].category + '</span>' +
                    '<p>' + data.message[i].content + '</p>' +
                    '<p class="text-right">' + '<i class="fa fa-user-o">' + '</i>' + data.message[i].number + '</p>' +
                    '<p>' + '<i class="fa fa-user-circle">' + '</i>' + '&nbsp;&nbsp;' + data.message[i].publisher + '&nbsp;&nbsp;&nbsp;&nbsp;' + data.message[i].time + '&nbsp;&nbsp;&nbsp;&nbsp;' + data.message[i].requirment + '</p>'
                    + '<div class="progress">' +
                    '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width:' + data.message[i].progress + ';">' +
                    data.message[i].progress +
                    '</div>'
                    + '</div>'
                    + '</div>');
                put_project.append(message);

            }
        },
        error: function (data) {

        }
});
var search=document.getElementById("search");
search.onkeyup(function(){
    search_text=search.val();

    $.ajax({
        type: "POST",
        url: "/checkproject",
        dataType: 'json',
        data: {
            search_text:search_text
        },
        success: function (data) {
            function isEmptyObject(e) {
                var t;
                for (t in e)
                    return !1;
                return !0;
            }
            put_project.innerHTML="";
            for (var i = 0; i < data.message.length; i++) {
                var message = $('<div class="row">' +
                    '<h4>' +
                    '<span class="badge">' + data.message[i].state + '</span>' +
                    data.message[i].name +
                    '</h4>' +
                    '<span class="label label-default">' + data.message[i].type + '</span>' + '<span class="label label-default">' + data.message[i].category + '</span>' +
                    '<p>' + data.message[i].content + '</p>' +
                    '<p class="text-right">' + '<i class="fa fa-user-o">' + '</i>' + data.message[i].number + '</p>' +
                    '<p>' + '<i class="fa fa-user-circle">' + '</i>' + '&nbsp;&nbsp;' + data.message[i].publisher + '&nbsp;&nbsp;&nbsp;&nbsp;' + data.message[i].time + '&nbsp;&nbsp;&nbsp;&nbsp;' + data.message[i].requirment + '</p>'
                    + '<div class="progress">' +
                    '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width:' + data.message[i].progress + ';">' +
                    data.message[i].progress +
                    '</div>'
                    + '</div>'
                    + '</div>');
                put_project.append(message);

            }
        },
        error: function (data) {

        }
    });
});
var amount_sort=document.getElementById("amount_sort");
var time_sort=document.getElementById("time_sort");
amount_sort.onclick(function(){
    $.ajax({
        type: "POST",
        url: "/checkproject",
        dataType: 'json',
        data: {
            sort_text:'价格排序'
        },
        success: function (data) {
            function isEmptyObject(e) {
                var t;
                for (t in e)
                    return !1;
                return !0;
            }
            put_project.innerHTML="";
            for (var i = 0; i < data.message.length; i++) {
                var message = $('<div class="row">' +
                    '<h4>' +
                    '<span class="badge">' + data.message[i].state + '</span>' +
                    data.message[i].name +
                    '</h4>' +
                    '<span class="label label-default">' + data.message[i].type + '</span>' + '<span class="label label-default">' + data.message[i].category + '</span>' +
                    '<p>' + data.message[i].content + '</p>' +
                    '<p class="text-right">' + '<i class="fa fa-user-o">' + '</i>' + data.message[i].number + '</p>' +
                    '<p>' + '<i class="fa fa-user-circle">' + '</i>' + '&nbsp;&nbsp;' + data.message[i].publisher + '&nbsp;&nbsp;&nbsp;&nbsp;' + data.message[i].time + '&nbsp;&nbsp;&nbsp;&nbsp;' + data.message[i].requirment + '</p>'
                    + '<div class="progress">' +
                    '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width:' + data.message[i].progress + ';">' +
                    data.message[i].progress +
                    '</div>'
                    + '</div>'
                    + '</div>');
                put_project.append(message);

            }
        },
        error: function (data) {

        }
    });
});
time_sort.onclick(function(){
    $.ajax({
        type: "POST",
        url: "/checkproject",
        dataType: 'json',
        data: {
            search_text:'时间排序'
        },
        success: function (data) {
            function isEmptyObject(e) {
                var t;
                for (t in e)
                    return !1;
                return !0;
            }
            put_project.innerHTML="";
            for (var i = 0; i < data.message.length; i++) {
                var message = $('<div class="row">' +
                    '<h4>' +
                    '<span class="badge">' + data.message[i].state + '</span>' +
                    data.message[i].name +
                    '</h4>' +
                    '<span class="label label-default">' + data.message[i].type + '</span>' + '<span class="label label-default">' + data.message[i].category + '</span>' +
                    '<p>' + data.message[i].content + '</p>' +
                    '<p class="text-right">' + '<i class="fa fa-user-o">' + '</i>' + data.message[i].number + '</p>' +
                    '<p>' + '<i class="fa fa-user-circle">' + '</i>' + '&nbsp;&nbsp;' + data.message[i].publisher + '&nbsp;&nbsp;&nbsp;&nbsp;' + data.message[i].time + '&nbsp;&nbsp;&nbsp;&nbsp;' + data.message[i].requirment + '</p>'
                    + '<div class="progress">' +
                    '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width:' + data.message[i].progress + ';">' +
                    data.message[i].progress +
                    '</div>'
                    + '</div>'
                    + '</div>');
                put_project.append(message);

            }
        },
        error: function (data) {

        }
    });
});




