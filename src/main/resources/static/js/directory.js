/**
 * Created by 杨玉卿 on 2018/3/30.
 */
var clear='';
$("#li_gj_item").click(function(){
    $.ajax({
        type:'POST',
        url:'/',
        dataType:'json',
        data:{},
        success:function(data){
            $.each(data,function(index,obj){
                if(index!=(data.length)){
                    var x,y;
                    var oUl=$("#ul");
                    oUl.html(clear);
                    var oLi=$('<li class="xiangmuming">'+
                        '<div class="my_div">' +
                        '<span class="glyphicon glyphicon-folder-close"></span>'
                        +'<span class="name">' +obj.name +'</span>' +
                        '</div>'+
                        '</li>');
                    oUl.append(oLi);
                    for(x in obj.branch){
                        var oLiUl=$('<ul class="fuzeren">'+
                                        '<span class="glyphicon glyphicon-user"></span>'+
                                        '<span class="person"> '+obj.branch[x].name+'</span>'+
                                    '</ul>');
                        oLi.append(oLiUl);
                        for(y in obj.branch[x].info){
                            var oLiUlLi=$('<li class="wenjian">' +
                                                '<span class="glyphicon glyphicon-pushpin"></span> ' +
                                obj.branch[x].info[y]+
                                            '</li>');
                            oLiUl.append(oLiUlLi);
                        }
                    }

                }
            })
        },
        error:function(){
            alert("请求失败");
        }

    });
});

