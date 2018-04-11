/**
 * Created by 杨玉卿 on 2018/4/10.
 */
//通知消息
$("#li_tz_item").click(function(){
    $.ajax({
        type:"POST",
        url:"/inform",
        dataType:"json",
        data:{

        },
        success:function(data){
            //输出data
            var oDiv=$("#inform");
            var clear='';
            oDiv.html(clear);
            var i;
            for(i in data){

                //name
                var oH2=$("<h2 class='leader my-h2'></h2>");
                oH2.append(data[i].name);
                oDiv.append(oH2);
                //info
                var oInfo=$("<div class='info'></div>");
                //日期
                var oDate=$("<h3 class='h3 my-h3'></h3>");
                oDate.append(data[i]['info'].day);
                oInfo.append(oDate);
                //通知消息
                var oMsg=$("<P class='lead my-lead'></P>");
                oMsg.append(data[i]['info'].msg);
                oInfo.append(oMsg);
                oDiv.append(oInfo);
            }
        },
        error:function(){
            alert("请求2失败！");
        }
    })
});