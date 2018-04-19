/**
 * Created by 杨玉卿 on 2018/4/10.
 */
/*关闭video*/
var video=document.getElementById('video');
    video.pause();
   $("#video").css("visibility","hidden");
//设置个人资料初始状态
    $("#profile").css('display','block');
    $("#second_profile").css('display','none');
//个人资料填充
function profile(data){
    $("#true_name1").text(data[0].name);
    if(data[0].gender=='gentleman'){
        data[0].gender='男';
    }
    else{data[0].gender='女'}
    $("#gender1").text(data[0].gender);
    $("#telephone1").text(data[0].phone);
    $("#introduce1").text(data[0].introduce);
    if(data[0].promise=="true"){
        data[0].promise='已签订';
    }
    else{data[0].promise='未签订';}
    $("#promise").text(data[0].promise);
    if(data[0].contract=="true"){
        data[0].contract='已签订';
    }
    else{data[0].contract='未签订';}
    $("#contract").text(data[0].contract);

}
//初始时加载一次资料信息
$.ajax({
    type:"get",
    url:"/staff/getUserInfo",
    dataType:"json",
    data:{},
    success:function(data){
       profile(data);
    },
    error:function(){
        alert("请求失败1");
    }
});
//编辑状态
    $("#edit").click(function(){
        $("#second_profile").css('display','block');
        $("#profile").css('display','none');

    });
    //保存时传一次更新后的个人资料
    $("#save").click(function(event){
        if($("#nick_name").val()==""||$("#true_name").val()==""||$("#telephone").val()==""||$("#introduce").val()==""){
            alert("您的资料还未填写完整，请填写完整后提交!");
            event.preventDefault();
        }
        else{
            var profile=new FormData(update_profile);
            $.ajax({
                type:'post',
                url:'/',
                dataType:"json",
                data:profile,
                processData: false,
                contentType: false,
                success:function(data){
                    //显示保存后的个人信息
                    profile(data);
                },
                error:function(){
                    alert("请求失败2");
                }
            })
        }
    });
